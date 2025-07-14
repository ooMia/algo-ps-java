tasks.register("mergeSources") {
    val sourceDir = layout.projectDirectory.dir("src/main/java")
    val outputFile = layout.buildDirectory.file("merged/Main.java")
    
    inputs.dir(sourceDir)
    outputs.file(outputFile)
    
    doLast {
        fun extractImports(content: String): Set<String> {
            return content.lines()
                .filter { it.trim().startsWith("import ") }
                .map { it.trim() }
                .toSet()
        }

        fun cleanSourceContent(content: String): String {
            return content.lines()
                .dropWhile { line -> 
                    val trimmed = line.trim()
                    trimmed.startsWith("package ") || 
                    trimmed.startsWith("import ") ||
                    trimmed.startsWith("//") ||
                    trimmed.isEmpty()
                }
                .filter { !it.trim().startsWith("System.err") }
                .joinToString("\n")
        }

        fun removePublicModifier(content: String): String {
            return content.lines()
                .map { line ->
                    val trimmed = line.trim()
                    if (trimmed.matches(Regex("^public\\s+(class|interface|enum|record|abstract\\s+class|final\\s+class)\\s+.*"))) {
                        line.replaceFirst("public ", "")
                    } else {
                        line
                    }
                }
                .joinToString("\n")
        }

        fun parseJavaDeclarations(content: String): Map<String, String> {
            val declarations = mutableMapOf<String, String>()
            val lines = content.lines()
            var currentDeclaration = StringBuilder()
            var declarationName = ""
            var braceCount = 0
            var inDeclaration = false
            
            for (line in lines) {
                val trimmed = line.trim()
                
                // 최상위 선언 시작 감지 (class, interface, enum, record)
                if (!inDeclaration && trimmed.matches(Regex("^(public\\s+)?(class|interface|enum|record|abstract\\s+class|final\\s+class)\\s+\\w+.*"))) {
                    // 이전 선언이 있다면 저장
                    if (declarationName.isNotEmpty() && currentDeclaration.isNotEmpty()) {
                        declarations[declarationName] = currentDeclaration.toString().trim()
                    }
                    
                    // 새 선언 시작
                    inDeclaration = true
                    braceCount = 0
                    currentDeclaration = StringBuilder()
                    
                    // 선언 이름 추출
                    val nameMatch = Regex("\\b(class|interface|enum|record)\\s+(\\w+)").find(trimmed)
                    declarationName = nameMatch?.groupValues?.get(2) ?: ""
                }
                
                if (inDeclaration) {
                    currentDeclaration.appendLine(line)
                    
                    // 중괄호 개수 세기
                    braceCount += line.count { it == '{' }
                    braceCount -= line.count { it == '}' }
                    
                    // 선언 끝 감지
                    if (braceCount == 0 && line.contains('}')) {
                        declarations[declarationName] = currentDeclaration.toString().trim()
                        inDeclaration = false
                        currentDeclaration = StringBuilder()
                        declarationName = ""
                    }
                }
            }
            
            // 마지막 선언 저장
            if (declarationName.isNotEmpty() && currentDeclaration.isNotEmpty()) {
                declarations[declarationName] = currentDeclaration.toString().trim()
            }
            
            return declarations
        }

        fun isInterface(declarationContent: String): Boolean {
            val firstLine = declarationContent.lines().firstOrNull { it.trim().isNotEmpty() }?.trim() ?: ""
            return firstLine.contains("interface ") && !firstLine.contains("implements")
        }

        fun isMainClass(declarationContent: String): Boolean {
            return declarationContent.contains("public class Main")
        }

        fun buildMergedContent(
            imports: Set<String>,
            mainContent: List<String>,
            classContents: List<String>,
            interfaceContents: List<String>
        ): String {
            val result = StringBuilder()
            
            // Add imports
            imports.sorted().forEach { result.appendLine(it) }
            result.appendLine()
            
            // Add Main class
            mainContent.forEach { 
                result.appendLine(it)
                result.appendLine()
            }
            
            // Add other classes
            classContents.forEach { 
                result.appendLine(it)
                result.appendLine()
            }
            
            // Add interfaces at the bottom
            interfaceContents.forEach { 
                result.appendLine(it)
                result.appendLine()
            }
            
            return result.toString()
        }

        fun validateJavaCompilation(javaFile: File): Boolean {
            return try {
                val compileResult = ProcessBuilder("javac", javaFile.absolutePath)
                    .directory(javaFile.parentFile)
                    .redirectErrorStream(true)
                    .start()
                
                val output = compileResult.inputStream.bufferedReader().readText()
                val exitCode = compileResult.waitFor()
                
                if (exitCode == 0) {
                    println("✅ Compilation successful!")
                    
                    // Clean up generated .class files
                    javaFile.parentFile.listFiles()?.filter { it.extension == "class" }?.forEach { it.delete() }
                    
                    true
                } else {
                    println("❌ Compilation failed with exit code: $exitCode")
                    println("Compilation errors:")
                    println(output)
                    false
                }
            } catch (e: Exception) {
                println("❌ Compilation validation failed: ${e.message}")
                false
            }
        }

        val sourceDirFile = sourceDir.asFile
        val outputFileFile = outputFile.get().asFile
        outputFileFile.parentFile.mkdirs()
        
        val allImports = mutableSetOf<String>()
        val mainClassContent = mutableListOf<String>()
        val otherClassContents = mutableListOf<String>()
        val interfaceContents = mutableListOf<String>()
        
        println("Scanning directory: ${sourceDirFile.absolutePath}")
        
        sourceDirFile.walkTopDown()
            .filter { it.isFile && it.extension == "java" }
            .forEach { file ->
                println("Processing file: ${file.name}")
                val content = file.readText()
                
                // Extract imports
                allImports.addAll(extractImports(content))
                
                // Clean and process content
                val cleanContent = cleanSourceContent(content)
                if (cleanContent.isNotBlank()) {
                    val declarations = parseJavaDeclarations(cleanContent)
                    
                    declarations.forEach { (name, declarationContent) ->
                        println("  → Found declaration: $name")
                        
                        when {
                            isMainClass(declarationContent) -> {
                                mainClassContent.add(declarationContent)
                                println("    → Added to mainClassContent")
                            }
                            isInterface(declarationContent) -> {
                                val modifiedContent = removePublicModifier(declarationContent)
                                interfaceContents.add(modifiedContent)
                                println("    → Added to interfaceContents")
                            }
                            else -> {
                                val modifiedContent = removePublicModifier(declarationContent)
                                otherClassContents.add(modifiedContent)
                                println("    → Added to otherClassContents")
                            }
                        }
                    }
                }
            }
        
        println("\nBuilding merged file...")
        println("Main classes: ${mainClassContent.size}")
        println("Other classes: ${otherClassContents.size}")
        println("Interfaces: ${interfaceContents.size}")
        
        val mergedContent = buildMergedContent(allImports, mainClassContent, otherClassContents, interfaceContents)
        outputFileFile.writeText(mergedContent)
        println("Merged file created at: ${outputFileFile.absolutePath}")
        
        // Validate compilation
        println("\nValidating Java compilation...")
        val isCompilationValid = validateJavaCompilation(outputFileFile)
        
        if (isCompilationValid) {
            println("🎉 Merged file is ready for submission!")
        } else {
            println("⚠️  Please check the merged file for compilation errors.")
        }
    }
}

from fractions import Fraction

N = int(input().strip())
A = list(map(int, input().split()))

# 조화평균 = 1 / sum(1/a)
s = sum(Fraction(1, a) for a in A)
h = Fraction(1, 1) / s

num, den = h.numerator, h.denominator
# 무조건 분수 형태
print(f"{num}/{den}")
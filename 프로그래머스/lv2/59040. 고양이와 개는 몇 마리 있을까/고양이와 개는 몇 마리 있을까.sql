-- 코드를 입력하세요
SELECT ANIMAL_TYPE, count(*) as count
from ANIMAL_INS
group by ANIMAL_TYPE
having ANIMAL_TYPE in ('Dog', 'Cat')
order by ANIMAL_TYPE;
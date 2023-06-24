-- 코드를 입력하세요
SELECT board.TITLE, board.BOARD_ID, reply.REPLY_ID, reply.WRITER_ID, reply.CONTENTS, date_format(reply.CREATED_DATE, '%Y-%m-%d') as CREATE_DATE
from USED_GOODS_BOARD board right outer join USED_GOODS_REPLY reply
on board.BOARD_ID = reply.BOARD_ID
where board.CREATED_DATE like '2022-10%'
order by reply.CREATED_DATE, board.TITLE

--------------------------------------------------------
--  파일이 생성됨 - 화요일-7월-25-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence BOARDCOMSEQ2
--------------------------------------------------------

   CREATE SEQUENCE  "KIC"."BOARDCOMSEQ2"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence BOARDSEQ2
--------------------------------------------------------

   CREATE SEQUENCE  "KIC"."BOARDSEQ2"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PRODUCTSEQ
--------------------------------------------------------

   CREATE SEQUENCE  "KIC"."PRODUCTSEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence CARTSEQ
--------------------------------------------------------

   CREATE SEQUENCE  "KIC"."CARTSEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence SURVEYSEQ
--------------------------------------------------------

   CREATE SEQUENCE  "KIC"."SURVEYSEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table ANSWER
--------------------------------------------------------

  CREATE TABLE "KIC"."ANSWER" 
   (	"SER" NUMBER(*,0), 
	"ID" VARCHAR2(20 BYTE), 
	"ANSGENDER" NUMBER(1,0), 
	"ANS1" VARCHAR2(30 BYTE), 
	"ANS2" VARCHAR2(60 BYTE), 
	"PRODNAME" VARCHAR2(50 BYTE), 
	"RDATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table BOARD2
--------------------------------------------------------

  CREATE TABLE "KIC"."BOARD2" 
   (	"NUM" NUMBER(*,0), 
	"BOARDID" VARCHAR2(3 BYTE), 
	"NAME" VARCHAR2(20 BYTE), 
	"SUBJECT" VARCHAR2(100 BYTE), 
	"CONTENT" VARCHAR2(2000 BYTE), 
	"IMAGE" VARCHAR2(300 BYTE), 
	"REGDATE" DATE, 
	"READCNT" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table BOARDCOMMENT2
--------------------------------------------------------

  CREATE TABLE "KIC"."BOARDCOMMENT2" 
   (	"SER" NUMBER(*,0), 
	"NUM" NUMBER(*,0), 
	"NAME" VARCHAR2(20 BYTE), 
	"CONTENT" VARCHAR2(2000 BYTE), 
	"REGDATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table CART
--------------------------------------------------------

  CREATE TABLE "KIC"."CART" 
   (	"CARTID" NUMBER(*,0), 
	"ID" VARCHAR2(20 BYTE), 
	"PRODNUM" NUMBER(*,0), 
	"PRODNAME" VARCHAR2(200 BYTE), 
	"QUANTITY" NUMBER(*,0), 
	"PRICE" NUMBER(*,0), 
	"RESULT" NUMBER(*,0), 
	"REGDATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table MEMBER2
--------------------------------------------------------

  CREATE TABLE "KIC"."MEMBER2" 
   (	"ID" VARCHAR2(20 BYTE), 
	"PASS" VARCHAR2(20 BYTE), 
	"NAME" VARCHAR2(20 BYTE), 
	"GENDER" NUMBER, 
	"TEL" VARCHAR2(20 BYTE), 
	"EMAIL" VARCHAR2(40 BYTE), 
	"ZIPCODE" VARCHAR2(10 BYTE), 
	"ADDRESS" VARCHAR2(100 BYTE), 
	"RDATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table PRODUCT
--------------------------------------------------------

  CREATE TABLE "KIC"."PRODUCT" 
   (	"PRODNUM" NUMBER(*,0), 
	"NAME" VARCHAR2(200 BYTE), 
	"PRICE" NUMBER(*,0), 
	"STOCK" NUMBER(*,0), 
	"INFO" VARCHAR2(2000 BYTE), 
	"IMAGE" VARCHAR2(200 BYTE), 
	"PRODGENDER" NUMBER, 
	"PRODANS1" VARCHAR2(20 BYTE), 
	"PRODANS2" VARCHAR2(20 BYTE), 
	"REGDATE" DATE, 
	"DETAIL" VARCHAR2(200 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into KIC.ANSWER
SET DEFINE OFF;
Insert into KIC.ANSWER (SER,ID,ANSGENDER,ANS1,ANS2,PRODNAME,RDATE) values (1,'admin',1,'fruit','cherry','테스트',to_date('23/07/25','RR/MM/DD'));
Insert into KIC.ANSWER (SER,ID,ANSGENDER,ANS1,ANS2,PRODNAME,RDATE) values (2,'admin',1,'wood','void','이솝 휠 오 드 퍼퓸',to_date('23/07/25','RR/MM/DD'));
REM INSERTING into KIC.BOARD2
SET DEFINE OFF;
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (4,'1','admin','공지사항 테스트3','사진 없음',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (2,'1','admin','공지사항 테스트 1','공지사항 입니다.','Creeper-icon.png',to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (3,'1','admin','공지사항 테스트2','공지사항 테스트2','pngwing.com.png',to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (5,'1','admin','공지사항 테스트4','공지사항 테스트4','Creeper-icon.png',to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (6,'1','admin','공지사항 테스트5','공지사항 테스트5','pngwing.com.png',to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (7,'1','admin','공지사항 테스트6','공지사항 테스트6','샘플1.jpg',to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (8,'1','admin','공지사항 테스트7','오','샘플2.jpg',to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (9,'2','admin','이벤트 테스트1 (~23.07.24)','이벤트 종료','샘플4.jpg',to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (10,'2','admin','이벤트 테스트2','2222',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (11,'2','admin','이벤트 테스트3','333333',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (12,'2','admin','이벤트 테스트4','4444444444',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (13,'2','admin','이벤트 테스트5','5555555555',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (14,'2','admin','이벤트 테스트6','6666666666',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (15,'1','admin','공지사항 테스트8','123',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (16,'1','admin','공지사항 테스트9','9999',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (17,'1','admin','공지사항 테스트10','101010101010',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (18,'1','admin','공지사항 테스트11','111',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (19,'1','admin','공지사항 테스트12','12121212',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (20,'1','admin','공지사항 테스트1313131','1313131313',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (21,'1','admin','공지사항 테스트14','14141414',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (22,'1','admin','공지사항 테스트15','151515',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (23,'1','admin','공지사항 테스트16','16161616',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (24,'1','admin','공지사항 테스트17','1717171717',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (25,'1','admin','공지사항 테스트18','181818',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (26,'1','admin','공지사항 테스트19','191919',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (27,'1','admin','공지사항 테스트20','202020',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (28,'1','admin','공지사항 테스트21','21212121',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (29,'1','admin','공지사항 테스트22','2222222222222',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (30,'1','admin','공지사항 테스트23','23333333333333',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (31,'1','admin','공지사항 테스트24','242424',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (32,'1','admin','공지사항 테스트25','252525',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (33,'1','admin','공지사항 테스트27','272727',null,to_date('23/07/25','RR/MM/DD'),0);
Insert into KIC.BOARD2 (NUM,BOARDID,NAME,SUBJECT,CONTENT,IMAGE,REGDATE,READCNT) values (34,'1','admin','공지사항 테스트 마지막','44545',null,to_date('23/07/25','RR/MM/DD'),0);
REM INSERTING into KIC.BOARDCOMMENT2
SET DEFINE OFF;
Insert into KIC.BOARDCOMMENT2 (SER,NUM,NAME,CONTENT,REGDATE) values (6,8,'parkpark','댓글3',to_date('23/07/25','RR/MM/DD'));
Insert into KIC.BOARDCOMMENT2 (SER,NUM,NAME,CONTENT,REGDATE) values (4,8,'kimkim','댓글',to_date('23/07/25','RR/MM/DD'));
Insert into KIC.BOARDCOMMENT2 (SER,NUM,NAME,CONTENT,REGDATE) values (5,8,'parkpark','댓글2',to_date('23/07/25','RR/MM/DD'));
Insert into KIC.BOARDCOMMENT2 (SER,NUM,NAME,CONTENT,REGDATE) values (7,34,'admin','admin 댓글',to_date('23/07/25','RR/MM/DD'));
Insert into KIC.BOARDCOMMENT2 (SER,NUM,NAME,CONTENT,REGDATE) values (8,34,'kimkim','kim 댓글',to_date('23/07/25','RR/MM/DD'));
Insert into KIC.BOARDCOMMENT2 (SER,NUM,NAME,CONTENT,REGDATE) values (9,34,'parkpark','park 댓글',to_date('23/07/25','RR/MM/DD'));
Insert into KIC.BOARDCOMMENT2 (SER,NUM,NAME,CONTENT,REGDATE) values (10,34,'parkpark','park 댓글2222',to_date('23/07/25','RR/MM/DD'));
Insert into KIC.BOARDCOMMENT2 (SER,NUM,NAME,CONTENT,REGDATE) values (11,34,'youyou','you 댓글',to_date('23/07/25','RR/MM/DD'));
REM INSERTING into KIC.CART
SET DEFINE OFF;
REM INSERTING into KIC.MEMBER2
SET DEFINE OFF;
Insert into KIC.MEMBER2 (ID,PASS,NAME,GENDER,TEL,EMAIL,ZIPCODE,ADDRESS,RDATE) values ('admin','admin','관리자',1,'010','010',null,null,to_date('23/07/25','RR/MM/DD'));
Insert into KIC.MEMBER2 (ID,PASS,NAME,GENDER,TEL,EMAIL,ZIPCODE,ADDRESS,RDATE) values ('parkpark','park','박지성',1,'019','123123',null,null,to_date('23/07/25','RR/MM/DD'));
Insert into KIC.MEMBER2 (ID,PASS,NAME,GENDER,TEL,EMAIL,ZIPCODE,ADDRESS,RDATE) values ('kimkim','kim','김민재',1,'010','123123',null,null,to_date('23/07/25','RR/MM/DD'));
Insert into KIC.MEMBER2 (ID,PASS,NAME,GENDER,TEL,EMAIL,ZIPCODE,ADDRESS,RDATE) values ('youyou','you','유재석',1,'010100','10101010',null,null,to_date('23/07/25','RR/MM/DD'));
REM INSERTING into KIC.PRODUCT
SET DEFINE OFF;
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (4,'프레시 헤스페리데스',125000,100,'상큼한 자몽 향에 달콤한 꽃과 과일향이 조화를 이루는 긍정적인 에너지로 가득한 시트러스 향','여성-꽃-시트러스.jpg',2,'flower','citrus',to_date('23/07/25','RR/MM/DD'),'꽃상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (3,'에르메스 상탈 마쏘이아',108000,100,'눈부신 햇살 속에서 파우더하게 번지는 로즈와 샌달우드의 담백 여성스러운 향기','여성-나무-샌달우드.jpg',2,'wood','sandal',to_date('23/07/25','RR/MM/DD'),'이솝 휠 오드 퍼퓸.png');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (5,'이솝 휠 오 드 퍼퓸',127000,100,'밤을 태워 먹은 편백나무 숲, 한 폭의 수묵화를 닮은 화취','남성-나무-편백.jpg',1,'wood','pyeonbaeg',to_date('23/07/25','RR/MM/DD'),'이솝 휠 오드 퍼퓸.png');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (6,'프레데릭 말 엉빠썽',174000,100,'시원한 워터리 그린 노트로 시작하지만 동시에 파우더리하고 달콤한 라일락 향이 아주 부드럽게 피부를 감싸요','남성-꽃-라일락.jpg',1,'flower','lilac',to_date('23/07/25','RR/MM/DD'),'꽃상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (7,'톰 포드 로스트 체리',100100,100,'베이스에 다크하면서 부드러운 우디노트, 위에는 적당히 달콤한 체리향과 은은한 아몬드 향으로 장식한 흔한 것 같으면서도 흔하지 않은 유니섹스 향수','남성-과일-체리.jpg',1,'fruit','cherry',to_date('23/07/25','RR/MM/DD'),'과일상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (8,'로데헤스페리데스 오 드 뚜왈렛',182800,100,'옅은 바람을 등지고 맡아지는 비터오렌지, 민트, 로즈마리의 프레시함이 마음을 톡톡 건드는 상쾌함','라벤더.jpg',2,'flower','lavender',to_date('23/07/25','RR/MM/DD'),'꽃상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (9,'오 로즈 오 드 퍼퓸',215800,100,'생장미 다발에서 피어오르는 장미의 깊은 향취와 포근한 잔향','장미.jpg',1,'flower','rose',to_date('23/07/25','RR/MM/DD'),'꽃상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (10,'오 로즈 오 드 뚜왈렛',135800,100,'세상 깔끔한 헤어미스트 같은 생장미 향기, 싱그러운 잔향','장미.jpg',2,'flower','rose',to_date('23/07/25','RR/MM/DD'),'꽃상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (11,'벨 드 그라스 오 드 뚜왈렛',80000,100,'바이올레스 라일락의 보라빛 향연에서 헬리오트로프가 감싸안는 따스함','라일락.jpg',2,'flower','lilac',to_date('23/07/25','RR/MM/DD'),'꽃상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (12,'너바나 프렌치 그레이',63000,100,'비맞은  라벤더 향이 나는 것 같은 시크한 표정의 뉴요커','남 라벤더.jpg',1,'flower','lavender',to_date('23/07/25','RR/MM/DD'),'꽃상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (13,'에이버리 오 드 뚜왈렛',42000,100,'센스있는 남자의 부드러운 우디-시트러스풍 깔끔함','시트러스.jpg',1,'flower','citrus',to_date('23/07/25','RR/MM/DD'),'꽃상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (14,'르 설리 오 드 퍼퓸',225000,100,'가녀린 손목에서 퍼지는 체리향 초콜릿, 하이얀 피부처럼 고운 아이리스의 나른한 꽃향기','체리.jpg',2,'fruit','cherry',to_date('23/07/25','RR/MM/DD'),'과일상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (15,'넥타린 블로썸 앤 허니 코롱',85800,100,'복숭아와 자몽을 크게 잘라 잎사귀와 함께 삼키는 듯한 달콤함','복숭아.jpg',1,'fruit','peach',to_date('23/07/25','RR/MM/DD'),'과일상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (16,'캔디 플로럴 오 드 뚜왈렛',113600,100,'딸기 칵테일에 사뿐히 올라간 꽃잎처럼, 부드럽게 연출하는 섹시함','딸기.jpg',1,'fruit','strawberry',to_date('23/07/25','RR/MM/DD'),'과일상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (17,'마이웨이 오 드 퍼퓸',79000,100,'생기발랄한 딸기, 투베로즈가 보석처럼 영롱하게 빛나는 순간','딸기.jpg',2,'fruit','strawberry',to_date('23/07/25','RR/MM/DD'),'과일상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (18,'아쿠아 디 콜로니아 에바',157950,100,'레몬과 드라이한 나무먼지가 살짝 흡수된 새로산 니트의 포근한 향기','레몬.jpg',1,'fruit','lemon',to_date('23/07/25','RR/MM/DD'),'과일상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (19,'아쿠아 데시마 오 드 퍼퓸',285000,100,'청청한 여름날, 나홀로 나무 아래를 가볍게 메우는 레몬의 푸릇함','레몬.jpg',2,'fruit','lemon',to_date('23/07/25','RR/MM/DD'),'과일상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (20,'오 트리쁠 목수 성 요셉',185000,100,'일렁이는 바닐라와 히노끼의 편백향이 나무기둥 사이로 천천히 스며드는 소프트-우디 향','편백나무.jpg',2,'wood','pyeonbaeg',to_date('23/07/25','RR/MM/DD'),'나무상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (21,'조이 바이 디올',131000,100,'눈부신 햇살 속에서 파우더하게 번지는 로즈와 샌달우드의 담백 여성스러운 향기','샌달우드.jpg',1,'wood','sandal',to_date('23/07/25','RR/MM/DD'),'나무상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (22,'알바 디 서울',157950,100,'찬바람에 흔들리는 소나무 가지에서 퍼지는 솔과 진액의 시크한 향기','소나무.jpg',1,'wood','sonamu',to_date('23/07/25','RR/MM/DD'),'나무상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (23,'파인보일',68600,100,'일렁이는 바다 위로 드리워진 파란 소나무와 만다린의 산뜻함, 휴양지의 향기','소나무.jpg',2,'wood','sonamu',to_date('23/07/25','RR/MM/DD'),'나무상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (24,'오드퍼퓸 보이드우드',59000,100,'푸릇한 잎들이 우거진 묵직한 나무들 사이로 불어오는 그윽한 우드의 향취','보이드우드.jpg',1,'wood','void',to_date('23/07/25','RR/MM/DD'),'나무상세.jpg');
Insert into KIC.PRODUCT (PRODNUM,NAME,PRICE,STOCK,INFO,IMAGE,PRODGENDER,PRODANS1,PRODANS2,REGDATE,DETAIL) values (25,'에이딕트 보이드우드',59000,100,'자스민의 그윽한 향기는 고급스러우면서도 모던한 이미지를 연출하며, 베이스 노트의 시더우드향은 차분하고 단정한 느낌을 선사합니다','여 보이드우드.jpg',2,'wood','void',to_date('23/07/25','RR/MM/DD'),'나무상세.jpg');

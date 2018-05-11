  CREATE TABLE TBLLOGIN
  (
     USERID VARCHAR2(20) CONSTRAINT CN_UID PRIMARY KEY,
     PASS   VARCHAR2(10) NOT NULL,
     VERIFY VARCHAR(5),
     STATUS VARCHAR2(1) DEFAULT 'N',
     WMODE VARCHAR2(10) DEFAULT 'STUDENT'
  )
  /

 insert into tblLogin values('prashant','akgec','0','Y','Admin')
 /
 

  CREATE TABLE TBLREGIS 
 (
    USERID VARCHAR2(20) CONSTRAINT CN_RID REFERENCES TBLLOGIN(USERID) ON DELETE CASCADE,
    FNAME  VARCHAR2(10) NOT NULL, 
    LNAME  VARCHAR2(10) NOT NULL, 
    MAILID VARCHAR2(50) CONSTRAINT CN_MAILID UNIQUE,
    DOB DATE,
    MOBILE NUMBER(10) CONSTRAINT CN_MOBILE UNIQUE, 
    ADDRESS VARCHAR2(200), 
    QUALI VARCHAR2(50) 
  )
  /

insert into tblRegis values('prashant','Prashant','Mishra','pmishra.akg@gmail.com','20-Jan-1976',9211420420,'R2/420, Raj Nagar GZB','MCA.')
/


create type mailtype as object
(
   sno number(4),
   senderid varchar(20),
   SUBJECT VARCHAR2(100),
   MSG VARCHAR2(3000),
   SENDDT DATE,
   MSIZE NUMBER(7),
   STATUS VARCHAR(1)
)
/

create type mails as table of mailtype
/

create table tblmails
(
   userid varchar2(20)  CONSTRAINT CN_MID REFERENCES TBLLOGIN(USERID) ON DELETE CASCADE,
   MTYPE varchar(10) NOT NULL,
   MAIL MAILS  -- mails is the tbltype of mailtype. 
)
nested table mail store as nest_table
/

-- to create procedure for registration
 
 CREATE OR REPLACE PROCEDURE pro_Regis
 (
     un varchar2,
     ps varchar2,
     fn varchar2,
     ln varchar2,
     mail varchar2,
     db varchar2,
     mb number,
     addr varchar2,
     q varchar2,
     verify varchar2,
     status varchar2,
     msg out varchar2
  )
  is
  begin
   insert into tblLogin(userid,pass,verify,status) values(un,ps,verify,status);
   insert into tblRegis values(un,fn,ln,mail,db,mb,addr,q);
   msg := 'User Created, U can Login Now';
   commit;
  exception 
   when no_data_found then  -- nye nahi aayega, ye tabhi aata hai jab select statement ho 
     msg :='Sorry';
   when others then 
     msg :='Exception is - '||sqlerrm;
  end;
  /
 -- procedure for send mail.
 CREATE OR REPLACE PROCEDURE pro_Send
 (
     u varchar2,
     ty varchar2,
     sid varchar2,
     sb varchar2,
     mesg varchar2,
     msg out varchar2
  )  
  is
    inx number(4); -- inbox last index
    sdx number(4); -- for outbox last index 
  begin
  -- select max sno for auto incr. 
  select max(sno) into inx from the(select mail from tblmails where userid=u and mtype='Inbox');
  select max(sno) into sdx from the(select mail from tblmails where userid=sid and mtype='Send');
  if inx is null then -- if inbox mail is empty. 
    inx := 1;
  else 
    inx := inx + 1;
  end if;
  if sdx is null then -- if send mail is empty. 
    sdx := 1;
  else 
    sdx := sdx + 1;
  end if;

  if inx = 1 then -- agar 1st mail hai to - agar inbox empty hai to 
   insert into TBLMAILS values(u,ty,mails(mailtype(inx,sid,sb,mesg,sysdate,length(mesg),'N')));
  else
   insert into the(select mail from tblMails where userid=u and mtype=ty) values(mailtype(inx,sid,sb,mesg,sysdate,length(mesg),'N'));
  end if;
  if sdx = 1 then  -- if send item is empty.
    insert into TBLMAILS values(sid,'Send',mails(mailtype(sdx,u,sb,mesg,sysdate,length(mesg),'N')));
  else
   insert into the(select mail from tblMails where userid=sid and mtype='Send') values(mailtype(sdx,u,sb,mesg,sysdate,length(mesg),'N')); 
  end if;
  msg := 'Message Successfully send...............';
  exception
   when no_data_found then -- - nye nahi aayega, ye tabhi aata hai jab select statement ho 
     msg :='Sorry';
   when others then 
     msg :='Exception is - '||sqlerrm;
  end;
  /


-- procedure for checkId    //      userid + pass in input , msg out for return the user status
create or replace procedure checkId(u varchar2,p varchar2,msg out varchar2)
is
begin
   select status into msg from tblLogin where userid = u and pass = p;
exception 
   when no_data_found then 
      msg := 'Sorry this is invalid Login Id and password, re-enter plz....';
end;
/

-- procedure for getMode    //      userid + pass in input , msg out for return the user status
create or replace procedure getMode(u varchar2,msg out varchar2)
is
begin
   select wmode into msg from tblLogin where userid = u;
exception 
   when no_data_found then 
      msg := 'Sorry this is invalid Login Id and password, re-enter plz....';
end;
/

create or replace procedure forget(u varchar2,m out varchar2)
is
begin
  select pass into m from tblLogin where userid=u;
exception
  when no_data_found then
    m:= 'Sorry this is invalid Login Id for forget Password';
end;
/

create or replace procedure getMailId(u varchar2,db varchar2,m out varchar2)
is
begin
  select mailid into m from tblRegis where userid=u and to_char(dob,'dd-mon-yyyy') = lower(db);
exception
  when no_data_found then
    m:= 'Sorry this is invalid DOB for forget Password';
end;
/

 CREATE OR REPLACE PROCEDURE ChangePass
 (
     u varchar2,
     op varchar2,
     np varchar2,
     msg out varchar2
  )  
  is
    p varchar2(10); -- for old pass 
  begin
    select pass into p from tblLogin where userid=u;
    if op = p then -- if old pass matched
       update tblLogin set pass = np where userid = u;
       msg:='Hello '||u||', ur Password Successfully Changed';
       commit;
  else 
       msg:='Sorry '||u||', ur old Password not matched.';
  end if;
  exception
   when no_data_found then -- - nye nahi aayega, ye tabhi aata hai jab select statement ho 
     msg :='Sorry '||u||', this is Invalid Login id for change Password.';
  end;
  /

create or replace procedure verifyId(u varchar2,v varchar2,msg out varchar2)
is
  ver varchar2(5); --for vcode from table
begin
   select verify into ver from tblLogin where userid = u;
   if v = ver then -- vcode matched
	msg:='yes';
	update tblLogin set status='Y';
	commit;
   else
        msg:='no';
   end if;
exception 
   when no_data_found then 
      msg := 'Sorry this is invalid Login Id and password, re-enter plz....';
end;
/

 CREATE OR REPLACE PROCEDURE pro_delMail
 (
     u varchar2,
     mt varchar2,
     sn number,
     msg out varchar2
  )
  is
    x number(4); -- inbox last index
    sid varchar(20);
    sb varchar(100);
    mesg varchar(3000);
    mdt varchar(15);
    MS NUMBER(7);
    ST VARCHAR(1);
  begin
  -- select max sno for auto incr.
  select max(sno) into x from the(select mail from tblmails where userid=u and mtype='Trash');
  select senderid,subject,msg,msize,to_char(senddt,'dd-Mon-YYYY'),status into sid,sb,mesg,ms,mdt,st from the(select mail from tblmails where userid=u and mtype=mt) where sno=sn;
  if x is null then -- if inbox mail is empty.
    x := 1;
  else
    x := x + 1;
  end if;
  if x = 1 then -- agar 1st mail hai to - agar inbox empty hai to
   insert into TBLMAILS values(u,'Trash:'||mt,mails(mailtype(x,sid,sb,mesg,mdt,ms,st)));
  else
   insert into the(select mail from tblMails where userid=u and mtype='Trash:'||mt) values(mailtype(x,sid,sb,mesg,mdt,ms,st));
  end if;
  delete from the(select mail from tblmails where userid=u and mtype=mt) where sno=sn;
  msg := 'Message Successfully deleted...............';
  exception
   when no_data_found then -- - nye nahi aayega, ye tabhi aata hai jab select statement ho
     msg :='Sorry';
   when others then
     msg :='Exception is - '||sqlerrm;
  end;
/

commit;
/



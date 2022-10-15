# first Web Site


### 웹 백엔드 연습용 종합 게시판 웹사이트입니다.

#### 활용한 tools & library
1. 스프링부트 2.7.4
2. thymeleaf
3. 메이븐
4. oracle DB / JDBC / Mybatis
5. sweetAlert2
6. bootstrap5.2v
7. Spring Security

#### 프로젝트 목표
1. 스프링부트와 사용한 도구들을 제대로 연결해서 프로젝트를 만들기
2. spring security 공부 및 적용
3. 웹과 db 연동
4. 게시판 CRUD 구현 - 조회수, 페이징 및 검색 처리

#### 목표 기능
1. 게시판
 - 글 리스트
 - CRUD
 - 댓글기능
2. 회원관리
 - session
 - 보안 / 접근 권한
3. 관리자페이지 
4. 채팅 기능

##### 개발 메모

2022-10-04 : thymeleaf 참조경로 다중경로, 레이아웃 적용 필요

2022-10-05 : thymeleaf 참조경로 정리, 레이아웃 간단 적용 완료. mybatis - oracle db연동 필요

2022-10-05 : mybatis dao 이용한 연결방식 boardList oracle db test 연동 성공 + board 게시판 DB 작성 및 연동

2022-10-06 : 1.게시글 상세보기 2.게시글 수정기능 추가. 3.게시판 css 수정 4. fetch & rest api 구성

2022-10-06 : 나머지 post, delete 기능 fetch와 rest api 구현 > url을 rest api 형식에 맞춰서 다시 구현해보기

2022-10-07 : 게시판 post, delete 기능 완성 / sweetAlert2 적용 > 게시판 페이징 & 검색 기능

2022-10-08 : 게시판 검색 기능 연결 > restful api의 검색 get url 의문점 : 검색 조건을 param으로 줄 때, 같은 기본 조회 컨트롤로 한번에 처리 불가능??

2022-10-08 : 조회수 기능 추가(글 상세 조회 controller 연결)

2022-10-09 : @Transactional - baordController 적용

> 검색 조회 / 전체 조회 같은 컨트롤러에 적용 @RequestParam(required=false) : 매개변수 없으면 null로 할당
> 
> 게시판 페이징 공통기능 구현하기

2022-10-10 : paging boardList페이지 및 pagination class 기본 설정 - db연결 필요

2022-10-11 기초 페이징 처리 구현

> spring security 활용 > 회원 관리 + 로그인 + 권한
>
> 스프링 시큐리티 전반적인 이해 및 공부 필요 > 기본 설정 방법 대체된 방법으로 연결 필요 (filter 빈 등록 방식)

2022-10-13 ~ 14 : 기본적인 시큐리티 회원 가입, 수정, 삭제완료, 기본 로그인/로그아웃/ 세션 기능 완료

> 탈퇴 시, 로그아웃 안되는 에러 : SecurityFilterChain - logout() 기능에서 로그아웃, 세션/쿠키 제거 기능 사용
> 
> 로그인 실패 핸들러 > 에러 처리 기능 추가 - 로그인 실패 핸들러 연결

2022-10-14 : 스프링 시큐리티 / 회원 인증&인가 관련 기능 정리
> 비밀번호 암호화 / 비밀번호 변경 및 저장 관련 문제 해결 필요
> 
> 게시물(board_no) - 댓글(board_no)  게시물(writer) - 회원(id) :  테이블 foreign key 연결 하기


__10일차 중간점검 - 앞으로의 요구 사항들__
- 1. spring security 이용해서 비밀번호 암호화 기능 관련 공부 및 적용
- 2. 게시판 및 유저 프로필에 이미지파일 업로드 기능 - 파일업로드 기능 적용
- 3. 파일 및 이미지 db에 효율적으로 저장하는 법?? > 게시글 내용 타입 blob 변경 고려
- 4. 채팅 기능
- 5. 자기소개 페이지 작성
- 6. 관리자 페이지 
- 7. 지도 api / Oauth2를 이용한 외부로그인 api (kakao / google) 활용

> 회원 삭제 시 해당회원이 작성한 게시글 / 댓글 : 살리고 회원만 없애기 > 회원도 delete yn 논리 삭제할 지 선택
> 
>  논리삭제 vs 물리삭제 문제 : 회원은 물리삭제, 게시글,댓글은 논리삭제이 가능할까?
>  
>  회원삭제 방법 1. delete_date 열을 추가해 모두 논리삭제 > but 회원 개인정보를 DB에 들고 있음
>  
>  회원삭제 방법 2. 프로시저를 이용하여 외래키 끄기 - 회원 물리삭제 - 외래키 켜기 - 게시물 / 댓글 논리삭제 수행

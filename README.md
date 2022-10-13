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
3. jdbc 트랜잭션 공부 및 적용
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

> 탈퇴 시, 로그아웃 안되는 에러
> 로그인 실패 핸들러 > 에러 처리 기능 추가

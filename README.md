# [Servlet/JSP Project(2023)] 놀이공원 웹사이트
‘지브리’ 테마를 접목시킨 놀이공원, DD Studio에 오신 것을 환영합니다!🎢<br>
DD Studio에서 제공하는 어트랙션 추천 기능으로 나에게 어울리는 어트랙션을 찾아보세요!

<br>

## 🔖 목차
- [📄 프로젝트 개요](#프로젝트-개요)
- [✒️ 기획 배경](#%EF%B8%8F기획-배경)
- [📌 구현 목표](#구현-목표)
- [👨‍👩‍👧‍👦 업무 분담](#업무-분담)
- [💻 주요 구현 기능](#주요-구현-기능)
- [📚 산출물](#산출물)

<br>

### 📄프로젝트 개요
- **프로젝트명**: DD Studio
- **분류**: Servlet/JSP Project
- **주제**: Servlet/JSP를 활용하여 제작한 놀이공원 웹사이트입니다. 놀이공원을 방문하는 회원들에게 여러가지 편의 기능을 제공하여 사용자의 편의성을 높이고자 하였습니다.
- **개발 환경**: HTML, CSS, JavaScript, jQuery, JSP, Java, Oracle Database 11g, Bootstrap, Eclipse, SQL Developer, DBeaver, eXERD, Draw.io, Git, GitHub, Sourcetree, Google Drive, Apache Tomcat
- **사용 기술**: Servlet, Ajax, Open API, JSTL, JDBC, JSON
- **주요 기능**: 액티비티·샵·티켓·할인·커뮤니티·이용 안내 등 조회 기능, 어트랙션 예약 기능, 어트랙션 추천 기능, 티켓 및 기프트샵 상품 구매 기능, 관리자 기능
- **담당 업무**: 액티비티(어트랙션, 영화, 페스티벌, 포토존) 사용자/관리자 기능
- **획득 역량**: 웹페이지 제작
- **프로젝트 기간**: 2023.10.30 ~ 2023.11.17 (19일)
- **벤치마킹**: 롯데월드 웹사이트(https://adventure.lotteworld.com/kor/main/index.do)
  <p><img src="https://github.com/smcha16/dd-land/assets/140796673/4783fe35-a148-4888-9fde-3f7261ea7a0f"></p>

<br>

### ✒️기획 배경
1. 어트랙션 예약 서비스 제공
2. 내 취향과 선호도에 맞춘 놀이기구 추천 및 추천 코스 제공
3. 놀이공원 내 기프트샵 판매 제품의 인터넷 구매 기능 제공

<br>

### 📌구현 목표
1. `회원/비회원/관리자별 기능 분리`
   1. 관리자
      - 요금/혜택 관리
      - 액티비티 관리
      - 추천 관리
      - 샵 관리
      - 가이드 관리
      - 소통 관리
      - 티켓 예매 관리
   2. 회원
      - 비회원 전체 기능
      - 마이페이지
      - 티켓 구매
      - 어트랙션 예약
      - 이용 문의 및 칭찬/불편/건의 게시글 작성
      - 리뷰 작성
      - 기프트샵 상품에대한 장바구니, 구매 기능
   3. 비회원
      - 요금/혜택·액티비티·추천·샵·가이드·소통 관련 조회 기능
      - 회원가입
2. `Open API 활용`
   - 위치 정보 Open API를 사용하여 사용자에게 지도 서비스 및 정확한 위치 정보 제공
3. `동적 페이지 제작`
   - Servlet/JSP를 활용하여 서버를 통해 송수신한 데이터로 페이지 제작

<br>

### 👨‍👩‍👧‍👦업무 분담
- 김형우: 요금/혜택
- **박나래**: 액티비티
- 이승원: 테스트, 로그인
- 이정은: 가이드, 관리자 공용
- 차민재: 샵, 티켓
- 차수민: 커뮤니티
- 황주원: 마이페이지

<br>

### 💻주요 구현 기능
1. 관리자 기능
   - 어트랙션 추가
     <img src="" alt="">
3. 회원 기능
   - 어트랙션 예약
     <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/0741a3b0-e7fd-4e75-9cca-d6b6bc8ab24b" alt="">
5. 회원/비회원 기능
   - 어트랙션 목록 조회
     <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/9a517893-5e38-4871-8a7e-4b1f446877ef" alt="">
   - 어트랙션 상세 조회
     <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/30824f39-f9f2-4e9e-a184-c57f797bf8de" alt="">
   - 페스티벌 목록 조회
     <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/5802131f-5c80-4ddd-bbbd-9def94e6d9ae" alt="">
   - 포토존 목록 조회
     <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/98e6dcd9-15d7-4c8b-bbf9-d90cae2fff18" alt="">

<br>

### 📚산출물
- 기획서
- 요구분석서 (49장)
- 순서도
- 데이터베이스 설계(ERD)
- 화면 설계서 (130장)
- 테이블 정의서(DDL) (50장)
- 데이터 정의서(DML) (188장)
- PPT
- 개발문서
- 요약본 (13장)

<details>
    <summary>산출물 미리보기 📷</summary>
        <div markdown="1">
            <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/7d488c42-7c27-4a3e-8abd-8b8245017f21" alt="기획서">
            <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/1d18c06f-9d08-490b-801e-d7d3f1f3cac0" alt="요구분석서">
            <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/9ac1943e-55f8-4ca1-94c7-e7768670b6a5" alt="순서도">
            <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/71c210bc-dee6-417a-bc38-3fd4ef8d6cc7" alt="데이터베이스 설계(ERD)">
            <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/b6f0a936-9457-42f3-bc64-7fd0e12b63ee" alt="화면설계서">
            <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/f61c48e8-c347-4703-acec-c2a582dd7e14" alt="테이블 정의서(DDL)">
            <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/d8e762de-7821-469b-b087-dac5114f56a8" alt="데이터 정의서(DML)">
            <img src="https://github.com/pengzer1/DD-Studio/assets/140796673/b4939132-5a02-498d-b545-45247078f71f" alt="요약본">
        </div>
</details>

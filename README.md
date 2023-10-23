### 호텔 체크인 키오스크
1. 기획의도
   기존 호텔 체크인 키오스크에서 이용자가 사용할 수 있는 기능이 한정되어 있다 생각하였고 이 점을 보완하여 다음과 같은 기능을 가진 키오스크를 구현하고자 하였다
   1. 간편 체크인
      - 데스크에서 웨이팅/직원의 도움 없이 고객이 직접 빠르고 간편하게 체크인 가능
    1. 무인 객실 구매
       - 기존 체크인 키오스크에서 예약자만 이용 가능한 점을 보완하여 현장에서도 객실 구매 가능
      1. 어메니티 및 식사 구매
         - 키오스크에 자판기 기능을 결합하여 시간과 장소에 구애 받지 않고 원하는 제품과 서비스 구매 가능
2. 프로그램 구성

   1. 예약 X -> 회원 X -> 개인정보 입력 후 객실 선택 -> 어매니티 및 식사 구매 -> 장바구니 -> 결제 및 정보 출력
   2. 예약 X -> 회원O -> 개인정보 입력 생략(회원정보에 있으므로) -> 어매니티 및 식사 구매 -> 장바구니 -> 결제 및 정보 출력
   3. 예약 시스템 모드(인터넷에서 예약하는 기능이라고 가정) 회원인지 물어본 후 객실 구매 -> 결제 및 정보 출력(어매니티는 체크인 하면서 구매 / 자판기이므로)
   4. 관리자 모드 
      1.  남녀, 연령, 객실 타입별 이용 현황(통계) 확인 가능
      2. 객실 총 매출, 남녀, 연령, 객실 타입별 매출 확인 가능
      3. 호텔 재고 관리(면도기, 샤워타올, 칫솔치약 등)
3. 코드 구현   
   1. Room 클래스에서는 예약이냐 당일이냐에 따라 체크인 날짜(시작 날짜)가 달라지기 때문에 구별함
   2. 인터페이스는 인터페이스의 적절한 예시가 아닌 것 같지만 비슷한 내용을 수행하는 두 클래스를 묶어주기 위해 사용함
   3. 직렬화 / 역직렬화 하는 것들은 저장되어야 할 것들. 예약 번호로 필요한 모든 정보에 접근이 가능하도록 함. 이제 실제 구현한 코드를 간단하게 볼 것
   4. flag -> 1일 ~ 2일의 101호 객체가 있고 3일 ~ 4일의 101호 객체가 있을 때 방 번호만으로 구분하면 겹치는 경우가 생김 -> 그걸 해결하기 위해 boolean 자료형의 변수를 활용
   5. while문을 돌면서 기존에 있던 101호 객체의 시작 ~ 종료 날짜의 범위가 오늘과 겹치는게 하나라도 있다면 체운 네모 출력 , flag를 true로 변경 boolean 배열은 사용자가 이용중인 방을 선택했을 때 입력하지 못하게 하려고 만든 배열임(코드의 중복을 피하기 위함) while문 앞에 flag는 다시 false로 세팅
   6. 밑에서 flag가 false라면 기존에 있던 객체의 시작 ~ 종료 날짜의 범위가 오늘과 겹치는게 없다라는 뜻이기 때문에 빈 네모 출력
4. 추가사항
   직렬화 / 역직렬화 목록
   1. ```<Hashtable <String, room> roomMap>``` 예약 번호 / 객실 객체
   2. ```int[] stockArray``` 호텔 재고 보유랑을 저장하는 int형 배열
   3. ```Hashtable<String, Reserve> Guest``` 예약번호 / 예약자 객체
   4. ```Hashtable<String, int[]>``` cusArray 예약번호 / 고객이 구매하는 어매니티 정보를 담는 int형 배열

5. 발표 자료
   https://drive.google.com/drive/u/0/folders/1L0VkWSJpj31TroSwKpXhbesBV-cFZBs_
### 후기
일단 나는 학원에 들어오기 전에 개인적으로 java공부를 조금 하고 왔는데 그 덕분에 학원에서 초반에 배우는 것들을 큰 어려움 없이 따라갈 수 있었고 사람들이 나를 잘하는 사람으로 생각하는걸 느낄 수 있었다. 그 때문일지는 몰라도 선생님께서 첫 프로젝트의 팀장을 뽑을 때 나는 팀장 4명 중 하나가 되어 팀장 역할을 맡게 되었다. 학교를 다닐 때와 지금의 팀플에 있어서 가장 큰 차이점은 팀장의 역할이라고 볼 수 있는데, 비교적 동등한 입장이었던 학교와는 달리 이곳에서는 모든 팀원들이 내가 가장 잘 하는 사람이라고 생각하고 내가 주도적으로 이끌어 나가기를 원한다는 점이었다. 내가 팀장이 된 후 자리를 팀끼리 앉는식으로 바꿔서 앉고 수업을 들으면서 팀원들이 수업을 듣다가 어려워 하는 부분이 있다는 것이 느껴지면 내 나름대로 설명해주려고 노력했다. 솔직히 말하면 내가 착한 사람이라서가 아니라 팀원들의 능력치가 올라야지 팀플을 할 때 조금 더 수월하게 진행할 수 있을 것 같아서 그렇게 했다. 마침애 팀플이 시작되고 우리 팀은 어떤 아이템을 진행할지 회의를 하기 시작했다. 선생님께서 기획안을 체크하고 합격해야지만 작업을 시작할 수 있다라고 하셔서 고민을 많이 했지만 팀원들 중 한 명이 호텔 키오스크라는 의견을 냈고 그 아이템이 좋다고 생각해서 그와 관련된 아이디어를 내기 시작했다. 어느새 대부분의 팀들이 기획안 작성을 마쳤고 팀별로 선생님께 아이템을 설명하는 시간을 가지게 되었는데 우리조는 운이 좋게 몇 가지 수정사항을 피드백 받고 이제 곧 추석 연휴이니 수정 작업이 완료되면 시작해도 좋을 것 같다라는 말씀을 해 주셨다. 그렇게 수업은 끝났고 추석 연휴가 시작됐다. 이 또한 우연이지만 나는 추석때 다른 사람들처럼 먼 지방에 며칠씩 머물지 않고 가까운 곳에 당일치기로 갔다오게 돼서 시간이 많았다. 그 덕에 나는 집에서 미리 호텔 키오스크의 핵심 부분인 객실 구매와 객실 예약을 짜 볼 시간이 있었고, 우리 팀과 비슷한 아이템을 선정한 다른 팀의 팀장과 많이 소통하며 도움을 주고 받으며 핵심 기능을 완성시켰다. 연휴가 끝나고 학원에 가서 내가 처음으로 팀원들에게 요구한 일은 내가 연휴동안 작성해온 코드들을 팀원들에게 설명하면서 같이 이해해보자 라는 것이었다. 팀원들을 모으고 내 코드를 설명한 이후에 우리가 필요한 기능들이 무엇이 남아 있는지 같이 체크했고 우리가 해야 할 부분들을 팀원들에게 나눴다. 나는 초반에 클래스 설계같은 초기작업들을 디테일하게 하지 않았는데 그 이유는 어차피 진행하면서 바뀔 것이 뻔하고 우리 모두가 이런 프로젝트가 처음이기에 미리 계획을 짜는 것에 있어서 한계가 있지 않을까 라는 생각을 가졌었다. 그래서 일단 진행하자는 식으로 탐원들에게 구두로 설명해주고 역할을 나눴는데 이렇게 하다보니 팀원들이 자신스스로 무엇을 해야 하는지 뚜렷하지 않아서 혼란스러워 하는 걸 느꼈다. 물론 그때마다 내가 할 수 있는 한 자세하게 설명해주고 예시를 보여주며 이해시키려고 노력했으나 어쩔 수 없이 내가 생각한 대로 완벽하게는 이루어 지지 않는 걸 보고 처음에 굉장히 디테일하게 미리 정해두고 할 걸 이라는 생각이 들었다.

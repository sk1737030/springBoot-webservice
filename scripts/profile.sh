#!/usr/bin/env bash

# 쉬고있는 profile 찾기: real1이 사용중이면 real2가 쉬고있고, 반대면 real1이 쉬고 이씀

function find_idle_profile() {
  #$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)
  # 현재 엔진엑스가 바라보고 있는 스플이 부트가 정상적으로 수행중인지 확인
  # 응답값을 httpstatus로 받는다
  # 정상이면 200 오류가 발생한다면 400~503사이로 발생하니 400이상은 모두 예외로 보고 real2를 현재 profile로 사용한다.
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ ${RESPONSE_CODE} -ge 400 ] # 400 보다 크면 (즉, 40x/50x 에러 모두 포함)

  then
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  if [ ${CURRENT_PROFILE} == real1 ]
  then
    IDLE_PROFILE=real2
  else
    IDEL_PROFILE=real1
  fi

  echo "${IDLE_PROFILE}"
}

# 쉬고 있는 profile의 port 찾기
function find_idle_port() {
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real1 ]
  then
    echo "8081"
  else
    echo "8082"
  fi
}
# bash라는 스크립트는 값을 반환하는 기능이 없다.
# 그래서 제일 마지막 줄에 echo로 결과를 출력 한후 클라이언트에서 그 값을 잡아서 $(find_idle_profile)을 사용한다.
# 중간에 echo를 사용해서는안된다.

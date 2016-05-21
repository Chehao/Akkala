echo 'package test result'
gradle  jacocoTestReport zipTest
cd build
TEST_FILE=$(ls -rt *.zip | tail -1)
echo $TEST_FILE
cd ..

echo 'send mail'

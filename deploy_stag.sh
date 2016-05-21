echo $AWS_DEFAULT_REGION
echo $AWS_ACCESS_KEY_ID
echo $AWS_SECRET_ACCESS_KEY

echo 'install AWS CLI'
pip install botocore --upgrade
pip install awscli --upgrade


echo 'gradle build and package'
gradle -version
gradle clean
gradle build -Penv=stg -x test --console=plain --stacktrace  

# rebuild to tar file for OpsWorks
cd quickstart/build/libs
VERSION=`git rev-parse --short HEAD`
WAR_FILE=$(ls -rt *.war | tail -1)
ZIP_FILE=${WAR_FILE}
ZIP_FILE_VERSION=${WAR_FILE%.*}-${VERSION}.war
echo "$ZIP_FILE_VERSION" "$ZIP_FILE"  "$WAR_FILE"


echo 'upload war to s3'
#aws s3 cp ${ZIP_FILE} s3://frs-aws-vpc-repo-us-east-1/project/build/stag/${ZIP_FILE}
#aws s3 cp ${ZIP_FILE} s3://frs-aws-vpc-repo-us-east-1/project/build/stag/${ZIP_FILE_VERSION}


echo 'rolling deployment'
# rolling deployment
# rolling deployment
#git clone https://github.com/awslabs/reinvent2014-scalable-site-management.git
#cd reinvent2014-scalable-site-management/opsworks-easy-deploy
#pip install -r requirements.txt
#python easy_deploy.py --opsworks-region us-east-1 --elb-region us-east-1 deploy --application=root rolling --stack-name=frs-stag-rules-web-app --layer-name='AppServer' --comment="Rolling deployment to all web api servers" --timeout 600


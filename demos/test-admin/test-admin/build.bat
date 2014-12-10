echo off
G:
SET XIAOXIAOBAI_HOME=%cd%

cd %XIAOXIAOBAI_HOME%
SET MAVEN_OPTS=-Xms192m -Xmx192m -XX:ReservedCodeCacheSize=64m -XX:MaxPermSize=64m

:mvn_command
ECHO 0-build fat jar�� �����±��������Ĺ���
ECHO 1-build fat jar�� ���������±��������Ĺ���
ECHO 2-build fat jar�� Ȼ��ֱ������jar��Ϊweb���̲��� ����web�˿�Ϊ1984 ������Լ��Ķ˿�

set /p isopt=��ѡ�����
if /i "%isopt%"=="0" goto mvn_build1
if /i "%isopt%"=="1" goto mvn_build2
if /i "%isopt%"=="2" goto mvn_build3

echo "��Чѡ���ѡ��(0-2)"
goto mvn_command

:mvn_build1
	echo [INFO]  build fat jar�� �����±��������Ĺ���
	call mvn clean package 
	goto mvn_end

:mvn_build2
	echo [INFO]  build fat jar�� ���������±��������Ĺ���
	cd ..\poms\test-admin
	call mvn clean install -Dmaven.test.skip=true 
	cd %XIAOXIAOBAI_HOME%
	call mvn clean package 
	goto mvn_end

:mvn_build3
	echo [INFO] build fat jar�� Ȼ��ֱ������jar��Ϊweb���̲��� ����web�˿�Ϊ1984 ������Լ��Ķ˿�
	cd ..\poms\test-admin
	call mvn clean install -Dmaven.test.skip=true 
	cd %XIAOXIAOBAI_HOME%
	call mvn clean package 
	cd %XIAOXIAOBAI_HOME%\target
	java -Dserver.port=8080 -Xms256m -Xmx256m -XX:PermSize=64m -Xss256k -XX:+DisableExplicitGC -XX:+UseParNewGC -XX:+UseCMSCompactAtFullCollection -XX:-CMSParallelRemarkEnabled -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xloggc:d:/logs/gc.log  -jar test-admin-2.0.jar
	goto mvn_end

:mvn_end

cd %XIAOXIAOBAI_HOME%
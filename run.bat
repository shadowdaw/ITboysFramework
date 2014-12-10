echo off
E:
SET XIAOXIAOBAI_HOME=%cd%

cd %XIAOXIAOBAI_HOME%
SET MAVEN_OPTS=-Xms192m -Xmx192m -XX:ReservedCodeCacheSize=64m -XX:MaxPermSize=64m

:mvn_command
ECHO 0-���ɿ�ܵ�eclipse��Ŀ�����ļ�
ECHO 1-���ɿ�ܵ�eclipse��Ŀ�����ļ�,ǿ�Ƹ���������ͬ�汾��jar��
ECHO 2-�����ܴ���,��ִ�в���
ECHO 3-�����ܴ���,ǿ�Ƹ���������ͬ�汾��jar�� ��ִ�в���
ECHO 4-���ɲ��Թ����̨eclipse��Ŀ�����ļ�
ECHO 5-���ɲ���ǰ̨��վeclipse��Ŀ�����ļ�

set /p isopt=��ѡ�����
if /i "%isopt%"=="0" goto mvn_eclipse
if /i "%isopt%"=="1" goto mvn_eclipse1
if /i "%isopt%"=="2" goto mvn_compile
if /i "%isopt%"=="3" goto mvn_compile1
if /i "%isopt%"=="4" goto mvn_test_admin
if /i "%isopt%"=="5" goto mvn_test_site

echo "��Чѡ���ѡ��(0-5)"
goto mvn_command

:mvn_eclipse
	echo [INFO]  ��ʼ����ƽ̨���eclipse�ļ�
	cd %XIAOXIAOBAI_HOME%
	call mvn clean eclipse:eclipse
	goto mvn_end

:mvn_eclipse1
	echo [INFO]  ��ʼ����ƽ̨���eclipse�ļ� ǿ�Ƹ�����ͬ�汾��jar��
	cd %XIAOXIAOBAI_HOME%
	call mvn clean -U eclipse:eclipse
	goto mvn_end

:mvn_compile
	echo [INFO]  ��ʼ������Կ��
	cd %XIAOXIAOBAI_HOME%
	call mvn clean install  -Dmaven.test.skip=true
	goto mvn_end

:mvn_compile1
	echo [INFO]  ��ʼ������Կ��
	cd %XIAOXIAOBAI_HOME%
	call mvn clean -U install  -Dmaven.test.skip=true
	goto mvn_end

:mvn_test_admin
        cd %XIAOXIAOBAI_HOME%\demos\poms\test-admin
        call mvn clean eclipse:eclipse
	goto mvn_end

:mvn_test_site
        cd %XIAOXIAOBAI_HOME%\demos\poms\test-site
        call mvn clean eclipse:eclipse
	goto mvn_end

:mvn_end

cd %XIAOXIAOBAI_HOME%
echo off
E:
SET XIAOXIAOBAI_HOME=%cd%

cd %XIAOXIAOBAI_HOME%
SET MAVEN_OPTS=-Xms192m -Xmx192m -XX:ReservedCodeCacheSize=64m -XX:MaxPermSize=64m

:mvn_command
ECHO 0-生成框架的eclipse项目工程文件
ECHO 1-生成框架的eclipse项目工程文件,强制更新依赖相同版本的jar包
ECHO 2-编译框架代码,不执行测试
ECHO 3-编译框架代码,强制更新依赖相同版本的jar包 不执行测试
ECHO 4-生成测试管理后台eclipse项目工程文件
ECHO 5-生成测试前台网站eclipse项目工程文件

set /p isopt=【选择命令】
if /i "%isopt%"=="0" goto mvn_eclipse
if /i "%isopt%"=="1" goto mvn_eclipse1
if /i "%isopt%"=="2" goto mvn_compile
if /i "%isopt%"=="3" goto mvn_compile1
if /i "%isopt%"=="4" goto mvn_test_admin
if /i "%isopt%"=="5" goto mvn_test_site

echo "无效选项，请选择(0-5)"
goto mvn_command

:mvn_eclipse
	echo [INFO]  开始生成平台相关eclipse文件
	cd %XIAOXIAOBAI_HOME%
	call mvn clean eclipse:eclipse
	goto mvn_end

:mvn_eclipse1
	echo [INFO]  开始生成平台相关eclipse文件 强制更新相同版本的jar包
	cd %XIAOXIAOBAI_HOME%
	call mvn clean -U eclipse:eclipse
	goto mvn_end

:mvn_compile
	echo [INFO]  开始编译测试框架
	cd %XIAOXIAOBAI_HOME%
	call mvn clean install  -Dmaven.test.skip=true
	goto mvn_end

:mvn_compile1
	echo [INFO]  开始编译测试框架
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
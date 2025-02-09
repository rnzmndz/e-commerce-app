@echo off
echo Finding process using port 8080...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080') do (
    set PID=%%a
)
if defined PID (
    echo Terminating process with PID %PID%...
    taskkill /PID %PID% /F
    echo Process terminated.
) else (
    echo No process found using port 8080.
)
pause

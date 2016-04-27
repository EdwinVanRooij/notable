echo off

rem Declare date variables
set year=%date:~-4,4%
set month=%date:~-10,2%
set day=%date:~-7,2%
set hour=%time:~-11,2%
set hour=%hour: =0%
set min=%time:~-8,2%

rem Set the date string for commit message
echo "Initializing strings..."
set datestring=""

rem Set the commit message
set commit_message="Added all changes"
set final_message="%commit_message% at %datestring% "

rem Add all files to the git head
echo "Adding all files to git head..."
git add .

rem Commit all changes
echo "Committing changes..."
git commit -m "Added all changes at %year%-%month%-%day%_%hour%.%min% - (automated commit from batch)"

rem Push to remote git repo
echo 'Pushing changes...'
git push

pause
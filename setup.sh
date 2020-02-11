#!/usr/bin/env bash

# setup all required variables
ES_MAN_HOME=~/.esman
FILE_SERVER_DIR="$ES_MAN_HOME/file_server"
FILE_SERVER_SCRIPT="run_file_server.groovy"

export ESMAN_HOME
export PATH=$PATH:$ESMAN_HOME:

createFileServerDir() {
    mkdir -p "$FILE_SERVER_DIR"
}

copyFileServerScript() {
    find . -iname "*.groovy*" -exec cp -- "{}" "$FILE_SERVER_DIR" \;
}

createAlias() {
touch ~/.bash_aliases
if ! alias share 2>/dev/null; then
    echo -e "alias share=\"groovy -classpath $FILE_SERVER_DIR $FILE_SERVER_DIR/$FILE_SERVER_SCRIPT\"\n" >> ~/.bash_aliases;
    source ~/.bash_aliases
fi
}

createFileServerDir
copyFileServerScript
createAlias

echo "Open new shell in order to use 'share' command line."

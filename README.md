# ugh
Commit messages beautified 💖 . Inspired from [kazupon/git-commit-message-convention](https://github.com/kazupon/git-commit-message-convention)

### Usage

Before ugh, my `commit` shortcut looked like this

```
function commit(){
    git add -A
    commitMsg=$(git commit -m "$1")
    echo "Hey, $commitMsg - THE END"
}
```

After ugh, my `commit` shortcut looks like this

```
function commit(){
	git add -A &&
	java -jar /path/to/ugh.jar
}
```

### Example

##### Before

`commit`

![](before_ugh_commit.png)


`result`

![](before_ugh_commit_result.png)


##### After

`commit`

![](after_ugh_commit.png)


`result`

![](after_ugh_commit_result.png)


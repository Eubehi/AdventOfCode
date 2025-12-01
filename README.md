# AdventOfCode

https://adventofcode.com/

## Environment

* IntelliJ IDEA 2025.2.5 (Community Edition)
* Gradle 9.2.1
* Java 25

## GIT

Git hooks are shared in the directory `.githooks/`. Configuring the property **core.hooksPath** (git >= 2.9) is
required to use them. This configuration is done when running the [setup_project.bash](scripts/setup_project.bash)
script. You can configure manually by running the following command: ``git config --local core.hooksPath .githooks/``.

## [Scripts](scripts)

All scripts shall be written in Bash.

Logging level is controlled through an environment property: `LOG_LEVEL`. Allowed levels are (case-sensitive):

* TRACE
* DEBUG
* INFO
* WARN
* ERROR
* FATAL

By default, `INFO` is considered.

### [setup_project.bash](scripts/setup_project.bash)

Run all commands used to set up properly the project (git hooks).

### [check_encoding.bash](scripts/check_encoding.bash)

List all files with encoding not supported. We do not put this script in pre-commit because it is slow on Windows.

### lib.*

Those scripts are libraries that other scripts can use. They have an _include_ mechanism that protect them from being
loaded multiple times (which can create issues with readonly variables). This mechanism is implemented by defining a
unique environment variable for each library. Here is the template to follow:

```bash
# Those two line make sure this library is sourced
: "${LIB_DIR:=$(dirname "${BASH_SOURCE[0]}")}"
source "$LIB_DIR/lib.source.bash"

# The unique environment variable that prevents multiple loading. Must start with LIB_
[[ -z ${LIB_XXX+x} ]] && export LIB_XXX= || return 0 # Cannot source it more than once

# How other libraries shall be sourced from a library
source "$LIB_DIR/lib.util.bash"
```

### [run_all_tests.bash](scripts/tests/run_all_tests.bash)

Run all tests (needs manual validation) defined on libraries.

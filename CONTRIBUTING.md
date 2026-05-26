# Contributing

Thanks for taking the time to look at this project.

## Running locally

```bash
git clone https://github.com/mahesh001-sys/selenium-automation-framework.git
cd selenium-automation-framework
mvn clean test
```

## Reporting a bug

Open an issue using the bug report template. Include the test name, the error message, and which browser you were on.

## Suggesting a test case

If you spot a missing scenario, open an issue with the title `[TEST] <what you want to cover>` and describe the expected behaviour.

## Code style

- One page class per page, extending `BasePage`
- No locators in test classes — they belong in page classes
- All waits through `WaitUtils` — no raw `Thread.sleep()`
- Test methods named `test<WhatItVerifies>` in camelCase

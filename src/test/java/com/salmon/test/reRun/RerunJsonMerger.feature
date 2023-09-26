Feature: Combine all the Json reports including rerun.

  @RerunJsonMerger
  Scenario: RerunJsonMerger
    When Merge rerun json report to original report
      | src    | target/cucumber-report/reRunFailed/cucumber.json |
      | target | target/cucumber-report/runwebat/cucumber.json    |
    Then Clear merge json report
      | target/cucumber-report/RerunJsonMerger/cucumber.json |
    Then Clear rerun json report
      | target/cucumber-report/reRunFailed/cucumber.json |


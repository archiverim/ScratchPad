---
Title: "Iteration 1 Retrospective"
Author: "Group 12 - ScratchPad"
Date: "June 3, 2026"
---

# Quick Retrospective
This retrospective uses the "quick retro" template provided but touches on the prompts in the worksheet outline.

## What went well?
- As a team, we were able to balance adding new features \([search bar](https://code.cs.umanitoba.ca/3350/summer2026/a01-g12-passwordmanagerapp/-/commit/02e39216f06e0fb89910126a7be2a21823a8cf15]\)) and refactoring ones that we had added in previous iterations. Previously, we just tried to force in as many features as possible.
- We were able to commit time to breaking down classes that were doing too much into smaller ones \(see [this commit](https://code.cs.umanitoba.ca/3350/summer2026/a01-g12-passwordmanagerapp/-/commit/d913d2d24a0689336094a8b5aeede13f6bdc5b9f)). In iteration one, we realized that we had certain classes that were doing too much.
- Regarding estimation, we understood our abilities and capacities from iteration 1 going into the week, and were able to identify which tasks needed to be done and how large of an undertaking they were. This led us to be able to know which features would be a "lost cause" to try and implement in this iteration.

## What went poorly?
- Regarding coordination, we struggled in this iteration. Specifically it was a busy week with  many other deadlines so we found that most of us were not able to stick to our "advance" deadlines specified in our team working agreement. Iteration 1 went smoother.
- Testing came at the last minute \(see [this commit](https://code.cs.umanitoba.ca/3350/summer2026/a01-g12-passwordmanagerapp/-/commit/6417b7c72f254a12072cda2612aa50a9474a3cac) and [this commit](https://code.cs.umanitoba.ca/3350/summer2026/a01-g12-passwordmanagerapp/-/commit/9a2e97e82d54a54cac67a4d4d822be89796e8efa)\), which resulted in some unit and integration tests which may not be thorough. Iteration 1 had more contributions to testing business logic.
- Some tasks such as wiring up a login workflow were underestimated in difficulty and had to be moved to the next iteration \(see [this branch](https://code.cs.umanitoba.ca/3350/summer2026/a01-g12-passwordmanagerapp/-/tree/15-Login-Business-Flow?ref_type=heads), which has login UI and a hacked together login flow\).

## What new ideas do we have?
- We would like to assign tasks earlier for iteration 3, and coordinate how to incorporate them with each other.
- In iteration 1 and 2 we had a "maximal" work items board in GitLab which showed all the tasks we *could* get done, and we ended up pushing any unfinished tasks to the next iteration. Instead, we will have a "minimal"  board which shows the items we *will* get done, and any items past that are bonuses.

## What actions will we take?
- Continue balancing refactoring and new features.
- Limit ourselves on quantity of work and focus on quality.
- Implement strong and thorough unit, integration and end-to-end tests.

## Summary
In summary, as a team we focused more on refactoring and code quality rather than code quantity in this iteration compared to iteration 1. The task breakdown did not change much, but we found ourselves adding many dev tasks and tech debt \(as seen [here](https://code.cs.umanitoba.ca/3350/summer2026/a01-g12-passwordmanagerapp/-/work_items/49) and [here](https://code.cs.umanitoba.ca/3350/summer2026/a01-g12-passwordmanagerapp/-/work_items/55)). Communication and coordination were okay and slightly better than iteration 1, but could still improve as we work towards our final goal. Lastly, we will focus more on test-driven development to ensure robust features are added.

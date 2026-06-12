# Team Working Agreement 
**Course:** COMP 3350 A01 Summer 2026 **Project Name:** ScratchPad **Team Name:** Group 12 **Date Created:** May 07, 2026 
## Team Members
| Name              | Program / Year (optional) | Preferred Name / Pronouns (optional) |
| ----------------- | ------------------------- | ------------------------------------ |
| Irfan, Rimsha     |                           |                                      |
| Malenko, Garrett  |                           | Garrett (he/him)                     |
| Nguyen, THANH DAT |                           | DAT                                  |
| Purvis, Ben       |                           | Ben (he/him)                         |
| Vasilev, Mikhail  |                           |                                      |
## 1. Shared Team Goals
As a team, we agree that our shared goals for this project are: 
- Meeting deadlines early, preferably 1-2 days prior to the course deadline to encourage a buffer period and time for review. 
- Learn how to work effectively in a team and utilize everyone’s strengths while encouraging growth in other areas. 
- Learn to set correct expectations and assign an equitable, manageable workload to all group members.
## 2. Individual Goals 
| Team Member | Individual Goals                                                                                                                                                                                                                                                                                                                                                |
| ----------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Mikhail     | Learn to effectively work in a team of new people and how to plan, organize and manage a large collaborative software project.                                                                                                                                                                                                                                  |
| Rimsha      | Oversee and manage the designing, planning, and coding of front end of the project. Deploy an aesthetic, readable and clean format of the project ensuring that it looks like a legit application. Manage and write all and any necessary documentation.                                                                                                        |
| Dat         | Learn collaborative project management using GitLab’s technical workflow. Help to implement robust 4-layer architecture by enforcing strict separation between logic and persistence. Thus, contributing to a stable, maintainable system while gaining practical experience in developing professional-grade software                                          |
| Garrett     | - Learn project management skills including best practices concerning dividing features into tasks and how to assign reasonable time estimates and acceptance criteria to features. <br>- Build a reliable application built upon an organized and maintainable codebase. <br>- Share my knowledge with team members and learn from them through collaboration. |
| Ben         | - Learn how to better use collaboratives development tools like Git / GitHub / Gitlab.<br>- Create a real application that continues to function even after the course is finished.<br>- Learn methods for creating unit tests to ensure code correctness.                                                                                                      |
## 3. Roles and Responsibilities 
Roles may rotate unless otherwise specified. Note: none of these are developer roles. You are all responsible for development as well and these are just recommended additional roles to define. 
| Role                | Description                                   | Assigned To  |
| ------------------- | --------------------------------------------- | ------------ |
| Project Coordinator | Organizes meetings, tracks deadlines          | Dat          |
| Technical Lead      | Oversees technical decisions and code quality | Group        |
| Documentation Lead  | Maintains README/docs                         | Rimsha       |
| GitLab Manager      | Manages repo structure, merges, issues        | Garrett, Ben |
| Quality Checker     | Reviews work before submission                | Mikhail      |
### Shared Roles and Responsibilities
**Technical Lead:** People will handle their own areas, volunteer to do code reviews and have group discussions about implementation details.

Roles will rotate as members want to experience multiple areas. 
## 4. Communication Plan 
### Primary communication channel
Discord
### Expected response time 
- Weekdays: within a day 
- Weekends: within a day
- During critical deadlines: 3 hours
### When is escalation required? 
- If there are 2 days with no communication.
- Or if there are 2 missed meetings with no notice or follow up.
## 5. Meetings 
### Regular meeting time(s)
Tuesday 11:45am-12:30pm.
### Meeting platform/location
In-person standup meetings on Tuesday, Discord progress update on Thursday night.
### Meeting norms (check all that apply): 
- ❌ Agenda shared in advance
- ✅ Start/end on time
- ❌ Cameras on (if online)
- ✅ Action items documented
- ✅ Everyone contributes
### What happens if someone cannot attend?
Notify the rest of the team prior to the meeting, then read the meeting minutes/notes and send your progress update through Discord.
## 6. Work Expectations 
### Task assignment
- Look at the issues on GitLab and self-assign tasks, taking the highest priority first.
- Notify others of which task you took.
### Definition of “done” for a task
- Meets acceptance criteria and all developer tasks are complete.
- The project builds successfully, all pre-existing and newly added tests pass.
### Code quality and documentation: 
- Block comments before functions and classes, as needed otherwise.
- Descriptive variable names, camelCase for local and instance variables, PascalCase for classes and interfaces, UPPER_CASE for constants.
- Private instance and static variables, use getters and setters instead of public access modifiers.
- Readable, minimize multiple layers of nesting in if-else statements, and reduce layering violations.
- 4 spaces indentation.
- Try/catches when needed.
- No repeated code, put into callable methods.
- Branches should be created per user story (or per dev task if shared work is required) and follow the naming standard `[GitLabTask#]_[Story_Name]`. Basically, it should be clear which user story a branch is for.
- Commit messages should start with `[#GITLABTASKNUMBER]` and then include a summary of the commit, for easy linking to user stories once merged into main.
## 7. Conflict Resolution & Unmet Expectations 
### Conflict mediation process 
1. Team discussion with at least 4 of 5 members including the offending member(s).
2. If there is no communication from the offending member(s), the other members will notify the offender(s) that they must act accordingly within a certain time frame and if not, members will escalate to the instructor.
3. Upon no change of behaviour, instructor escalation.
### How will concerns be raised respectfully? 
Focus on what the issue is rather than blaming a person or people. Be open and respectful while clearly presenting an evidence-based problem (in the form of a Git commit, issue tracking, attendance or communication) and solutions to said problem.
## 8. Responsible GenAI Use  
As a team, we have discussed the responsible and ethical use of Generative AI tools (e.g., ChatGPT, Copilot) for this project. We agree on the following shared norms: 
### Allowed uses of GenAI in this project
- Assistance in explaining complex concepts and trade-offs of architectural decisions (in a StackOverflow manner).
- Help with debugging if we are stuck and seeing odd behaviour in our code.
- Generating fake data for testing or recommending test edge cases that we may overlook.
- Reviewing documentation grammar and suggestions for UI styling.
### Disallowed uses of GenAI in this project
- “Prompting” or vibe coding. No generating code with a coding assistant such as GitHub Copilot or Claude Code for direct use in the project.
- Similarly, no blind copy-pasting if given suggestion code blocks from GenAI.
### Identified risks of GenAI use
- Correctness. Especially if copying and pasting chunks into our codebase blindly, we could have affect the long-term reliability and maintainability of our project.
- Over-reliance. We want to know what the purpose is of each line in our codebase.
- Readability and reduction of understanding. Especially if not given full context of the project, GenAI can produce code that is overly complex and hard to trace through
### Ensuring transparency, accountability and shared understanding of AI-assisted work: 
- Code review and merge request flags. At the bare minimum, if a section of code was developed with suggestions from GenAI we require this to be mentioned explicitly in the merge request description. This way, the code reviewer can pay special attention to that section and confirm the logic before it gets merged.
- Communication. We recommend that an AI-assisted work be labeled as exactly that (not necessarily as code comments, but maybe within a commit message or message to our Discord channel) and acknowledge this does not absolve the committer of responsibility regarding correctness or quality of the submitted work.
## 9. Accountability
If a team member repeatedly misses deadlines or responsibilities, the team will: 
- Reach out to them regarding their behaviour.
- Reflect this behaviour in our peer reviews.
- Collect evidence that we have acknowledged the lack of accountability and have made attempts to encourage responsibility.
- If it happens more than once, notify this member that we will be contacting the instructor and then share our evidence with the instructor in an email sent to all group members.
## 10. Agreement 
By signing below, we confirm that we have discussed this document together and agree to follow it.
| Name              | Date         |
| ----------------- | ------------ |
| Irfan, Rimsha     | May 12, 2026 |
| Malenko, Garrett  | May 12, 2026 |
| Nguyen, THANH DAT | May 12, 2026 |
| Purvis, Ben       | May 12, 2026 |
| Vasilev, Mikhail  | May 12, 2026 |

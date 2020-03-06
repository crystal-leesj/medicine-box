
# Software Requirements

## Vision

Create an Alexa application capable of interacting with a user's medication. Once medicines are added to a user's Alexa device, our application will create reminders for scheduled doses, have the ability to query if the user is up-to-date with their medications, and get various details about their medicines.

Our application removes the burden and worry of taking regimented and scheduled medications.  Our application will remind you when you should take which medicine and when. It also will provide feedback about your current medications, and whether you are on schedule for prescribed doses. This application is targeted at the elderly or anybody whose life is hectic and busy, and wish to be reminded about their medication instead of having to memorize their doses.


## Scope (In/Out)
### IN

- The skill will provide information to the users about all the different commands within the skill
- The skill will provide user the ability add medication within a database
- The skill will provide user a reminder to take a unique medication
- The skill will provide user the ability input if a unique medication at a unique time has been taken

### OUT

- The skill will not display data
- The skill will not access anyone else's information


## Minimum Viable Product 

Our minimum requirements include building an Alexa Skill using the Alexa SDK that will allow users to interact with the device to add and get details about their medications.

### Stretch Goals

- Add more commands
- User to Medication relationship
- list medications and the next dose time

## Functional Requirements

A user can update their medication
A user can add brand name and generic medications
Skill can remind user of taking medication
Skill can list all medications
A user can update if or if not, the medication was taken

## Data Flow

The user opens Medicine Box using a phrase command to personal Alexa device. User then can ask for description that triggers Alexa to list the commands available within the skill. User is able to add medication with guidance from a medical professional to input the requirements which utilizes a dialog prompts from Alexa. That data is then transformed to a schedule, so that the Alexa device is able to remind the user to take the medication. The user is then able to input if or if not, the medication was taken.

## Non-Functional Requirements (301 & 401 only)

### Security

Due to HIPPA, the data must be secure for the user. There should not be any data leakage without the user consent. This can be done through IAM policies emplaced by AWS.

### Usability

User should be able to traverse through the skill easily. Given the intended audience, it must be simple commands. Keeping in mind, memory issues, the skill must prompt users and list all available commands to the user. This can be implemented by when user initiates the skill, a prompt to command Alexa to "describe" will list all available commands. The commands will be simplified with addition prompts to help guide the user along.

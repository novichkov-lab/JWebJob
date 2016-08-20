# JWebJob

JWebJob is a Web Project written in Java to be used as an interface between a computational process and its user.

JWebJob consists of three client-side web pages, one to accept input, one to display progress while the input is processed, and one to display the result of the process.

The process of proceeding through each of these pages in order is a "Java Web Job".

Input page:
* Contains sample input fields for items such as the job name and job options.
* Contains a File Upload form which allows for large file uploading by Ajax.
* Contains a submit button to submit the job parameters.
* Saves user input to a database for further use.
 
Progress page:
* Displays the progress of each individual step of the underlying process, called a task, to the user visually.
* Each task has its own status, which is updated after each step of a multi-step process and displayed by Ajax.
* Proceeds to the Result page when status of the job is marked to be completed.
 
Result page:
* Displays data resulting from the process to the user.


JWebJob follows the Model-View-Controler type 2 scheme, which seperates the processes and data from what the user sees. The views are very adjustable to any custom job.

For more information take a look at JWebJobSlides, particularly the configuration section and checklist to set up JWebJob in a way that works for your project.

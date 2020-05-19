from bs4 import BeautifulSoup
import requests
import time

"""
Takes in an array of sections (in the form of strings) and 
parses the strings and removes garbage
Also removes sections from the list entirely if it contains 
a string in sectionsToRemove
"""
def removeGarbage(sections, prefix_to_remove, sectionsToRemove):
    retList = []
    for section in sections:
        if (ord(section.text[0]) < 120 and ord(section.text[0]) != 10):
            addList = section.text
            for word in sectionsToRemove:
                if word in addList:
                    addList = ""
            for prefix in prefix_to_remove:
                addList = addList.replace(prefix, '')
            if addList != "":
                retList.append(addList.strip(" "))
    return retList


def writeToFile(lecture, otherActivity, activityName, term):
    #remove the "Lecture" or "Discussion" and so on
    lecture = lecture.replace("Web-Oriented Course", "") if "Web-Oriented Course" in lecture else lecture.replace("Lecture", "")
    otherActivity = otherActivity.replace(activityName, "")
    
    #remove the term number
    courseTerm = lecture[3]
    lecture = lecture[:3] + lecture[4:]
    otherActivity = otherActivity[:3] + otherActivity[4:]

    print(courseTerm + ", " + lecture + ", " + otherActivity)


coursename = "CPSC"
coursenum = "304"
term = 1
result = requests.get("https://courses.students.ubc.ca/cs/courseschedule?pname=subjarea&tname=subj-course&dept=" + coursename + "&course=" + coursenum)

#200 means ok, 404 means error
if (result.status_code != 200): 
    print("Course is invalid")

src = result.content
soup = BeautifulSoup(src, 'lxml')
sections = soup.find_all("tr")

# for section in sections:
#     if (ord(section.text[0]) < 120 and ord(section.text[0]) != 10):
#         print(section.text)
        
#     select = True
# print("Remove garbage: \n")

#first list is the substring i want to remove from the sectionj
#second list is a list where if the section contains it, we remove from the returnList completely
parsedSections = removeGarbage(sections, ["Full", "Blocked", "Restricted", coursename, coursenum], ["Waiting List"])
# for section in parsedSections:
#     print(section)

lecture = " "
otherActivity = " "

containsOtherActivity = False
for section in parsedSections:
    if "Discussion" in section or "Tutorial" in section or "Laboratory" in section:
        containsOtherActivity = True

for section in parsedSections:

    if "Discussion" in section or "Tutorial" in section or "Laboratory" in section:
        activity = "Discussion" if "Discussion" in section else "Tutorial" if "Tutorial" in section else "Laboratory"
        writeToFile(lecture, section, activity , term)

    else:
        lecture = section # the first thing on the list is the lecture so this will be initialized
        if not containsOtherActivity:
            writeToFile(lecture, "", "", term)

  
        


    




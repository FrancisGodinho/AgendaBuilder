from bs4 import BeautifulSoup
import requests
import time

"""
Takes in an array of sections (in the form of strings) and 
parses the strings and removes garbage
"""
def removeGarbage(sections, prefix_to_remove):
    retList = []
    for section in sections:
        if (ord(section.text[0]) < 120 and ord(section.text[0]) != 10):
            addList = section.text
            for prefix in prefix_to_remove:
                addList = addList.replace(prefix, '')
            retList.append(addList[1:] if addList[0] == ' ' else addList)
    return retList

#DOES NOT WORK=============================================================
def removeElementFromList(list, element):
    for item in list:
        if(element in item):
            list.remove(item)
    return list

coursename = "CPSC"
coursenum = "320"
result = requests.get("https://courses.students.ubc.ca/cs/courseschedule?pname=subjarea&tname=subj-course&dept=" + coursename + "&course=" + coursenum)

#200 means ok, 404 means error
if (result.status_code != 200): 
    print("Course is invalid")

src = result.content
soup = BeautifulSoup(src, 'lxml')
sections = soup.find_all("tr")


for section in sections:
    if (ord(section.text[0]) < 120 and ord(section.text[0]) != 10):
        print(section.text)
    select = True

print("Remove garbage: \n")



removedSections = removeElementFromList(sections, "Waiting")

parsedSections = removeGarbage(removedSections, ["Full", "Blocked", "Restricted"])
for section in parsedSections:
    print(section)




    




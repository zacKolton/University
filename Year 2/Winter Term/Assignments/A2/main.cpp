//-----------------------------------------
// NAME				: Zac Kolton
// STUDENT NUMBER	: 7838513
// COURSE			: COMP 2150
// INSTRUCTOR		: Mr. Boyer
// ASSIGNMENT		: #2 Simulation
// QUESTION # 1
//
// REMARKS: Create a event driven simulation that simulates the aspects of a hosptial ER (Emergency Room)
//
//
//-----------------------------------------

#include <iostream>
#include <cstdlib>
#include <fstream>
#include <sstream>
#include <string>
#include <stdio.h>
#include "Simulation.cpp"
using namespace std;


void runProgram(char *file);
void processEvents(ListItem *assessEvent, ListItem *bloodWEvent, ListItem *xRayEvent,ListItem *treatmentEvent, List *eventList);

int main(int argc, char *argv[]){
    if(argc < 2){
        cout << "Usage: " << argv[0] << endl;
        exit(1);
    }
    cout << "Simulation begins...\n";
    runProgram(argv[1]);
    cout << "\n...Simulation complete.\n\n";
    cout << "\nEnd of processing.\n";
    return 0;
}// main



void runProgram(char *file)
{
    ifstream *inFile = new ifstream;
    inFile->open(file);
    string line;
    getline(*inFile,line);
    int id = 1;
    List *eventList = new EventList();
    Station *assessStation = new Assessment(new Queue());
    Station *bloodWStation = new BloodWork(new PQueue());
    Station *xRayStation   = new Xray(new PQueue());
    Station *treatmentStation = new Treatment(new PQueue());
    if((line.empty()) || (line.at(0) == '#' ))
    {
        cout << line << endl;
        getline(*inFile,line);
    }
    else
    {
        
        Patient *patient = new Patient(line,id);
        ListItem *arrival = new Arrival(patient);
        eventList->insert(arrival);
        while(!eventList->isEmpty())
        {
            ListItem *event = eventList->remove();
            ListItem *assessEvent    = assessStation->process(event);
            ListItem *bloodWEvent    = bloodWStation->process(event);
            ListItem *xRayEvent      = xRayStation->process(event);
            ListItem *treatmentEvent = treatmentStation->process(event);
            processEvents(assessEvent, bloodWEvent, xRayEvent,treatmentEvent,eventList);
            if(getline(*inFile,line))
            {
                patient = new Patient(line, ++id);
                arrival = new Arrival(patient);
                eventList->insert(arrival);
            }
        }
    }
}

void processEvents(ListItem *assessEvent, ListItem *bloodWEvent, ListItem *xRayEvent,ListItem *treatmentEvent, List *eventList)
{
    if(assessEvent != NULL)
    {
        eventList->insert(assessEvent);
    }
    else if(bloodWEvent != NULL)
    {
        eventList->insert(bloodWEvent);
    }
    else if(xRayEvent != NULL)
    {
        eventList->insert(xRayEvent);
    }
    else if(treatmentEvent != NULL)
    {
        eventList->insert(treatmentEvent);
    }
}




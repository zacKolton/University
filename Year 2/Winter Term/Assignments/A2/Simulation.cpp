//Class Name	:
//Class Type	:
//Purpose		:

#include <stdio.h>
#include <string>
#include <iostream>
#include <sstream>
#include "A2Const.cpp"
using namespace std;



//------------------------------------------
//------------------------------------------
//Class Name    : ListItem
//Class Type    : Abstract parent class
//Purpose       : Provide polymorpishm for all objects that will be going into a list/queue/priority queue
//------------------------------------------
//------------------------------------------

class ListItem {
public:
    virtual void print() = 0;
};

//------------------------------------------
//Class Name    : Patient
//Class Type    : Subclass of ListItem
//Purpose       : Read in from file all information about a particular patient
//					- Use to hold all information in one place for the simulation
//------------------------------------------
class Patient: public ListItem{
private:
    int arriveTime;
    int assessTime;
    int priority;
    string requirment;
    int treatTime;
    int numID;
    int waitTime;
    int serviceTime;
    int currentTime;
    
public:
    Patient();
    Patient(string l, int n);
    bool needsB();
    bool needsX();
    int getCurrentTime();
    int getArrivalTime();
    int getAssessTime();
    int getPriority();
    int getTreatmentTime();
    int getID();
    int getWaitTime();
    int getServiceTime();
    void resetWaitTime();
    void print();
    void increaseServiceTime(int n); //not used so far
    void increaseWaitTime(int n);
    void updateCurrTime(int n);
    string getRequirment();
};

//----------------
// CONSTRUCTOR
//----------------

Patient::Patient(string line, int n)
{
    istringstream *iss = new istringstream(line);
    *iss >> arriveTime >> assessTime >> priority >> requirment >> treatTime;
    waitTime    = 0;
    serviceTime = 0;
    currentTime = arriveTime;
    numID = n;
}

//----------------
// METHODS
//----------------


bool Patient::needsB()
{
    return requirment.at(0) == 'B';
}

bool Patient::needsX()
{
    return requirment.at(1) == 'X';
}

void Patient::print()
{
    cout << "Patient#: " << numID  << "\t";
}

void Patient::increaseWaitTime(int n)
{
    waitTime += n;
}



void Patient::increaseServiceTime(int n)
{
    serviceTime += n;
}

void Patient::resetWaitTime() { waitTime = 0; }
void Patient::updateCurrTime(int n){ currentTime = n;}

//----------------
// GETTERS
//----------------

int Patient::getArrivalTime()  {return arriveTime; }
int Patient::getAssessTime()   {return assessTime; }
int Patient::getPriority()     {return priority;   }
int Patient::getTreatmentTime(){return treatTime;  }
int Patient::getWaitTime()     {return waitTime;   }
int Patient::getServiceTime()  {return serviceTime;}
int Patient::getCurrentTime()  {return currentTime;}
int Patient::getID()           {return numID;      }
string Patient::getRequirment(){return requirment; }



//------------------------------------------
//Class Name    : Event
//Class Type    : Subclass of ListIem
//Purpose       : Create events that drive the program and simulate a ER
//					- This holds a patient that contians information for the timing of the event
//------------------------------------------
class Event: public ListItem{
protected:
    int time;
private:
    Patient *p;
    int ID;
public:
    Event(Patient *p);
    virtual void print();
    virtual bool needsB();
    virtual bool needsX();
    virtual int getTime();
    virtual int getID();
    virtual Patient* getPatient();
};

//----------------
// CONSTRUCTOR
//----------------

Event::Event(Patient *p)
{
    this->p = p;
    ID = p->getID();
}

//----------------
// METHODS
//----------------

void Event::print()
{
    cout <<"Time Unit: "<< time << "\t";
    p->print();
}

bool Event::needsB() { return p->needsB(); }
bool Event::needsX() { return p->needsX(); }


//----------------
// GETTERS
//----------------
int Event::getID()   { return ID;   }
int Event::getTime() { return time; }
Patient* Event::getPatient() { return p;}

//------------------------------------------
//Class Name    : Arrival
//Class Type    : Subclass of Event
//Purpose       : update simulation when a new patient arrives
//------------------------------------------

class Arrival: public Event {
public:
    Arrival(Patient *p);
    void print();
};

//----------------
// CONSTRUCTOR
//----------------

Arrival::Arrival(Patient *p) : Event(p)
{
    time = p->getCurrentTime();
}

//----------------
// METHODS
//----------------

void Arrival::print()
{
    Event::print();
    cout << "Arrives in ED";
}

//------------------------------------------
//Class Name    : StartAssessment
//Class Type    : Subclass of Event
//Purpose       : Update simulation when the the patient is able to be assessed
//------------------------------------------

class StartAssessment: public Event {
private:
    int assessTime;
public:
    StartAssessment(Patient *p);
    void print();
};

//----------------
// CONSTRUCTOR
//----------------

StartAssessment::StartAssessment(Patient *p) : Event(p)
{
    time       = p->getCurrentTime() + p->getWaitTime();
    assessTime = p->getAssessTime();
    p->updateCurrTime(time);
}

//----------------
// METHODS
//----------------

void StartAssessment::print()
{
    Event::print();
    cout << "Starting assessment -- Takes time: " << assessTime << endl;
}

//------------------------------------------
//Class Name    : CompleteAssessment
//Class Type    : Subclass of Event
//Purpose       : Update simulation when the assessment is completed
//------------------------------------------

class CompleteAssessment: public Event {
private:
    int priority;
    bool bloodWork;
    bool xRay;
public:
    CompleteAssessment(Patient *p);
    void print();
};

//----------------
// CONSTRUCTOR
//----------------

CompleteAssessment::CompleteAssessment(Patient *p) : Event(p)
{
    time         = p->getCurrentTime() + p->getAssessTime();
    priority     = p->getPriority();
    bloodWork    = p->needsB();
    xRay         = p->needsX();
    p->updateCurrTime(time);
}

//----------------
// METHODS
//----------------

void CompleteAssessment::print()
{
    Event::print();
    cout << "Assement Completed -- Assigned priority: " << priority << " -- Scheduled for: ";
}



//------------------------------------------
//Class Name    : StartXRay
//Class Type    : Subclass of Event
//Purpose       : Update simulation when the patient is able to get an xray
//------------------------------------------

class StartXRay: public Event {
public:
    StartXRay(Patient *p);
    void print();
};

//----------------
// CONSTRUCTOR
//----------------

StartXRay::StartXRay(Patient *p) : Event(p)
{
    time = p->getCurrentTime();
    
}

//----------------
// METHODS
//----------------

void StartXRay::print()
{
    Event::print();
    cout << "Start xRay" << endl;
}

//------------------------------------------
//Class Name    : StartBloodWork
//Class Type    : Subclass of Event
//Purpose       : Update simulation when the patient is able to get bloodwork done
//------------------------------------------

class StartBloodWork: public Event {
public:
    StartBloodWork(Patient *p);
    void print();
};

//----------------
// CONSTRUCTOR
//----------------

StartBloodWork::StartBloodWork(Patient *p) : Event(p)
{
    time = 0; //figure out
}

//----------------
// METHODS
//----------------

void StartBloodWork::print()
{
    Event::print();
    cout << "Start Blood Work" << endl;
}

//------------------------------------------
//Class Name    : CompleteXray
//Class Type    : Subclass of Event
//Purpose       : Update simulation when the patient has completed the xray
//------------------------------------------

class CompleteXRay: public Event {
public:
    CompleteXRay(Patient *p);
    void print();
};

//----------------
// CONSTRUCTOR
//----------------

CompleteXRay::CompleteXRay(Patient *p) : Event(p)
{
    time = 0; //figure out
}

//----------------
// METHODS
//----------------

void CompleteXRay::print()
{
    Event::print();
    cout << "Xray completed" << endl;
}

//------------------------------------------
//Class Name    : CompleteBloodWork
//Class Type    : Subclass of Event
//Purpose       : Update simulation when the patient has completed the bloodwork
//------------------------------------------

class CompleteBloodWork: public Event {
public:
    CompleteBloodWork(Patient *p);
    void print();
};

//----------------
// CONSTRUCTOR
//----------------

CompleteBloodWork::CompleteBloodWork(Patient *p) : Event(p)
{
    time = 0; //figure out
}

//----------------
// METHODS
//----------------

void CompleteBloodWork::print()
{
    Event::print();
    cout << "Blood Work completed" << endl;
}

//------------------------------------------
//Class Name    : StartTreatment
//Class Type    : Subclass of Event
//Purpose       : Update simulation when the patient is ready to start treatment
//------------------------------------------

class StartTreatment: public Event {
public:
    StartTreatment(Patient *p);
    void print();
};

//----------------
// CONSTRUCTOR
//----------------
StartTreatment::StartTreatment(Patient *p) : Event(p)
{
    time = 0; //figure out
}

//----------------
// METHODS
//----------------

void StartTreatment::print()
{
    Event::print();
    cout << "Start Treatment" << cout;
}
//------------------------------------------
//Class Name    : Discharge
//Class Type    : Subclass of Event
//Purpose       : Update simulation when the patient is done treatment
//------------------------------------------

class Discharge: public Event {
public:
    Discharge(Patient *p);
    void print();
};
//----------------
// CONSTRUCTOR
//----------------

Discharge::Discharge(Patient *p) : Event(p)
{
    time = 0; //figure out
}

//----------------
// METHODS
//----------------

void Discharge::print()
{
    Event::print();
    cout << "Discharged" << endl;
}


//------------------------------------------
//------------------------------------------
// END LIST ITEM
//------------------------------------------
//------------------------------------------



//------------------------------------------
//Class Name    : Node
//Class Type    : Parent class
//Purpose       : Hold a ListItem for the list(s)
//------------------------------------------

class Node{
private:
    ListItem* item;
    Node* next;
    
public:
    Node();
    Node(ListItem *i);
    Node(ListItem *i, Node *n);
    ListItem* getItem();
    void setItem(ListItem *i);
    void setNext(Node *n);
    Node* getNext();
    void print();
};

//----------------
// CONSTRUCTORS
//----------------

Node::Node()
{
    item = NULL;
    next = NULL;
}

Node::Node(ListItem *i)
{
    item = i;
    next = NULL;
}

Node::Node(ListItem *i, Node *n)
{
    item = i;
    next = n;
}

//----------------
// METHODS
//----------------

void Node::print()
{
    item->print();
}

//-----------------
// GETTERS/SETTERS
//----------------

ListItem* Node::getItem(){ return item;}
Node* Node::getNext(){return next;}
void Node::setItem(ListItem *i) {item = i;}
void Node::setNext(Node *n) {next = n;}

//------------------------------------------
//------------------------------------------
// END NODE
//------------------------------------------
//------------------------------------------


//------------------------------------------
//Class Name    : List
//Class Type    : Parent class
//Purpose       : Hold a list of Nodes
//------------------------------------------
class List {
protected:
    Node *first;
    int size;
public:
    virtual void insert(ListItem *i) = 0;
    virtual ListItem* remove();
    virtual bool isEmpty();
    virtual int getSize();
    virtual int getWaitTime() = 0;
    virtual void print(); // for testing
    virtual bool search(int n);
    List();
    
};

//----------------
// CONSTRUCTOR
//----------------

List::List() {
    first = NULL;
    size  = 0;
}

//----------------
// METHODS
//----------------


ListItem* List::remove()
{
    ListItem *result = NULL;
    if(!isEmpty())
    {
        result = first->getItem();
        first = first->getNext();
    }
    return result;
}

bool List::search(int n)
{
    bool found = false;
    if(!isEmpty())
    {
        Node * curr = first;
        while(curr != NULL)
        {
            if((dynamic_cast<Patient*>(curr->getItem())->getID()) == n)
            {
                found = true;
            }
            curr = curr->getNext();
        }
    }
    return found;
}
 


bool List::isEmpty() {return first == NULL;}
int List::getSize() { return size;}

//for testing
void List::print()
{
    Node* currNode = first;
    while(currNode != NULL)
    {
        currNode->print();
        currNode = currNode->getNext();
    }
    cout << endl;
}

//------------------------------------------
//Class Name    : EventList
//Class Type    : Subclass of List
//Purpose       : Hold a list of events
//------------------------------------------

class EventList: public List {
public:
    void insert(ListItem *e);
    int getWaitTime();
    EventList();
};

//----------------
// CONSTRUCTOR
//----------------
EventList::EventList() : List() {}

//----------------
// METHODS
//----------------

void EventList::insert(ListItem *e)
{
    if(List::isEmpty())
    {
        first = new Node(e);
    }
    else
    {
        Node *curr = first;
        Node *prev = NULL;
        while((curr != NULL) && ((dynamic_cast<Event*>(e)->getTime()) > (dynamic_cast<Event*>(curr->getItem()))->getTime()))
        {
            prev = curr;
            curr = curr->getNext();
        }
        if(prev == NULL)
        {
            prev = new Node(e,curr);
            first = prev;
        }
        else
        {
            prev->setNext(new Node (e));
        }
        
        size++;
    }
}

int EventList::getWaitTime()
{
    return 0;
}
//-------------------------------------------
//Class Name    : Queue
//Class Type    : Subclass of List
//Purpose       : Hold a Queue of patients for the assessment Station
//-------------------------------------------

class Queue: public List {
private:
    Node* last;
public:
    void insert(ListItem *i);
    bool isEmpty();
    int getWaitTime();
    Queue();
};

//----------------
// CONSTRUCTOR
//----------------

Queue::Queue() {first = last = NULL; size = 0;}

//----------------
// METHODS
//----------------

int Queue::getWaitTime()
{
    int wait = 0;
    if(!isEmpty())
    {
        Node *curr = first;
        while(curr != NULL)
        {
            wait += dynamic_cast<Patient*>(curr->getItem())->getAssessTime();
            curr = curr->getNext();
        }
    }
    return wait;
}


void Queue::insert(ListItem *i)
{
    if(isEmpty())
    {
        first = new Node(i,last);
    }
    else
    {
        Node* newLast = new Node(i);
        last->setNext(newLast);
        last = newLast;
    }
    size++;
}
bool Queue::isEmpty(){ return (List::isEmpty()) && (last == NULL);}


//-------------------------------------------
//Class Name    : PQueue
//Class Type    : Subclass of List
//Purpose       : Create a priority queue of Patients based on their priority
//-------------------------------------------

class PQueue: public List {
private:
    Node *last;
public:
    void insert(ListItem *p);
    bool isEmpty();
    int getWaitTime();
    PQueue();
};

//----------------
// CONSTRUCTOR
//----------------

PQueue::PQueue(): List() { last = NULL;}

//----------------
// METHODS
//----------------


int PQueue::getWaitTime()
{
    int wait = 0;
    Node *curr = first;
    Patient *pPtr = NULL;
    while(curr != NULL)
    {
        pPtr = dynamic_cast<Patient*>(curr->getItem());
    }
    return wait;
}

void PQueue::insert(ListItem *i)
{
    if(isEmpty())
    {
        first = last = new Node(i);
    }
    else
    {
        Node *curr = first;
        Node *prev = NULL;
        while((curr != NULL) && (dynamic_cast<Patient*>(i)->getPriority()) >= (dynamic_cast<Patient*>(curr->getItem())->getPriority()))
        {
            prev = curr;
            curr = curr->getNext();
        }
        if(prev == NULL)
        {
            prev = new Node(i,curr);
            first = prev;
        }
        else
        {
            prev->setNext(new Node(i));
        }
        size++;
    }
}

bool PQueue::isEmpty() {return (List::isEmpty()) && (last == NULL); }

//------------------------------------------
//------------------------------------------
// END LIST
//------------------------------------------
//------------------------------------------


//------------------------------------------
//Class Name    : Station
//Class Type    : Subclass of ListItem
//Purpose       : Hold a queue/priority queue
//					- Processs events
//------------------------------------------

class Station: public ListItem{
protected:
    int totalPatients;    // Patients <---- figure out how to effieciently add to all these
    int totalST;        // Total Service Time
    int totalWT;        // Total Wait Time
    int averageWT;        // Average Wait Time
    int averageST;        // Average Service Time
    List *list;
public:
    Station(List *l);
    virtual void print();
    virtual ListItem* process(ListItem *i) = 0;
    virtual void insert(ListItem *i);
    virtual bool isAvailable() = 0;
    virtual ListItem* remove();
    virtual int getWaitTime();
    virtual bool search(int n);
};

//----------------
// CONSTRUCTOR
//----------------

Station::Station(List *l)
{
    totalPatients = 0;
    totalST       = 0;
    totalWT       = 0;
    averageWT     = 0;
    averageST     = 0;
    list = l;
}

//----------------
// METHODS
//----------------


int Station::getWaitTime()
{
    return list->getWaitTime();
}


ListItem * Station::remove()
{
    return list->remove();
}

bool Station::search(int n)
{
    return list->search(n);
}


void Station::insert(ListItem *i)
{
    list->insert(i);
}

void Station::print()
{
    cout << totalPatients << "\t" << totalST << "\t" << averageST << "\t" << totalWT << "\t" << averageWT << endl;
}

//------------------------------------------
//Class Name    : Assessment
//Class Type    : Subclass of Station
//Purpose       : Processes events relating to an assessment
//					- checks if its related by dynamically casting
//------------------------------------------

class Assessment: public Station {
public:
    Assessment(List *l);
    ListItem* process(ListItem *i);
    bool isAvailable();
    void print();
};

//----------------
// CONSTRUCTOR
//----------------

Assessment::Assessment(List *l) : Station(l){}

//----------------
// METHODS
//----------------

bool Assessment::isAvailable()
{
    return list->getSize() <= A2Const::numAssessmentNurses;
}

ListItem* Assessment::process(ListItem *i)
{
    ListItem* result = NULL;
    if(dynamic_cast<Arrival*>(i))
    {
        Patient *p = dynamic_cast<Arrival*>(i)->getPatient();
        dynamic_cast<Arrival*>(i)->print();
        if(isAvailable())
        {
            cout << " -- Nurse available " << endl;
            result = new StartAssessment(p);
        }
        else
        {
            
            p->increaseWaitTime(Station::getWaitTime());
            Station::insert(p);
            cout << " -- Nurse Unavailable, Queued" << endl;
        }
    }
    if(dynamic_cast<StartAssessment*>(i))
    {
        dynamic_cast<StartAssessment*>(i)->print();
        if(!list->isEmpty())
        {
            result = new CompleteAssessment(dynamic_cast<Patient*>(Station::remove()));
        }
        else
        {
            result = new CompleteAssessment(dynamic_cast<StartAssessment*>(i)->getPatient());
        }
    }
    return result;
}

void Assessment::print()
{
    cout << "Assessment\t";
    Station::print();
    
}

//------------------------------------------
//Class Name    : Blood Work
//Class Type    : Subclass of Station
//Purpose       : Processes events relating to Bloodwork and completeAssesments
//------------------------------------------
class BloodWork: public Station {
public:
    BloodWork(List *l);
    ListItem* process(ListItem *i);
    bool isAvailable();
    void print();
};

//----------------
// CONSTRUCTOR
//----------------

BloodWork::BloodWork(List *l) : Station(l){}

//----------------
// METHODS
//----------------


bool BloodWork::isAvailable()
{
    return list->getSize() <= A2Const::numBloodTech;
}

//you will see "!Station::search(dynamic_cast<CompleteAssessment*>(i)->getID())"
//	- After realizing my design was flawed (blood work and xray problem) i tried to work around it with limited time and thought creating a search function would work
//		- to check if the patient was already in the queue after passing it another complete assesment event
//		- I would have wanted it to bounce to the Xray (and vise versa) but the design is wrong
//	- This did not work because the patient can only be in one queue at once
ListItem* BloodWork::process(ListItem *i)
{
    ListItem* result = NULL;
    if(dynamic_cast<CompleteAssessment*>(i) && dynamic_cast<CompleteAssessment*>(i)->needsB() && !Station::search(dynamic_cast<CompleteAssessment*>(i)->getID()))
    {
        //dynamic_cast<CompleteAssessment*>(i)->print();
        //cout << "Blood Work" << endl;
        Patient *p = dynamic_cast<CompleteAssessment*>(i)->getPatient();
        if(isAvailable())
        {
            result = new StartBloodWork(p);
        }
        else
        {
            
        }
    }
    if(dynamic_cast<StartBloodWork*>(i))
    {
       // dynamic_cast<StartBloodWork*>(i)->print();
    }
    if(dynamic_cast<CompleteBloodWork*>(i))
    {
    }
    return result;
}

void BloodWork::print()
{
    cout << "Blood Work\t";
    Station::print();
    
}

//------------------------------------------
//Class Name    : Xray
//Class Type    : Subclass of Station
//Purpose       : Processes events relating to Xray and completeAssessment
//					- Unfinished
//------------------------------------------
class Xray: public Station {
public:
    Xray(List *l);
    ListItem* process(ListItem *i);
    bool isAvailable();
    void print();
};

//----------------
// CONSTRUCTOR
//----------------

Xray::Xray(List *l) : Station(l){}

//----------------
// METHODS
//----------------

bool Xray::isAvailable()
{
    return list->getSize() <= A2Const::numXRayTech;
}

ListItem* Xray::process(ListItem *i)
{
    ListItem* result = NULL;
    if(dynamic_cast<CompleteAssessment*>(i) && dynamic_cast<CompleteAssessment*>(i)->needsX())
    {
        dynamic_cast<CompleteAssessment*>(i)->print();
        cout << "xRay";
    }
    if(dynamic_cast<StartXRay*>(i))
    {
    }
    if(dynamic_cast<CompleteXRay*>(i))
    {
        
    }
    return result;
}
void Xray::print()
{
    cout << "X-ray\t";
    Station::print();
    
}

//------------------------------------------
//Class Name    : Treatment
//Class Type    : Subclass of Station
//Purpose       : Processes events relating to treatment and discharge
//					- Unfinished
//------------------------------------------



class Treatment: public Station {
public:
    Treatment(List *l);
    ListItem* process(ListItem *i);
    bool isAvailable();
    void print();
};

//----------------
// CONSTRUCTOR
//----------------

Treatment::Treatment(List *l) : Station(l){}

//----------------
// METHODS
//----------------

bool Treatment::isAvailable()
{
    return list->getSize() <= A2Const::numDoctors;
}


ListItem* Treatment::process(ListItem *i)
{
    ListItem* result = NULL;
    if(dynamic_cast<StartTreatment*>(i))
    {
    }
    if(dynamic_cast<Discharge*>(i))
    {
        //print out summary table 
    }
    return result;
}


void Treatment::print()
{
    cout << "Treatment\t";
    Station::print();
    
}







    

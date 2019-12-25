//
//  A2Const.cpp
//  A2KoltonZac
//
//  Created by Zac  Kolton on 2019-02-19.
//  Copyright © 2019 Zac Kolton. All rights reserved.
//

#include <stdio.h>

class A2Const{
public:
    static const int numAssessmentNurses;
    static const int numBloodTech;
    static const int numXRayTech;
    static const int numDoctors;
    static const int bloodWorkTime;
    static const int XRayTime;
    
};

const int A2Const::numAssessmentNurses = 2;
const int A2Const::numBloodTech = 3;
const int A2Const::numXRayTech = 1;
const int A2Const::numDoctors = 3;
const int A2Const::bloodWorkTime = 15;
const int A2Const::XRayTime = 25;


#include<iostream>

#include <boost/any.hpp>
#include <boost/lexical_cast.hpp>

#include "GoolmathImpl.h"

#include "stdio.h"
#include "fstream"
#include "cstdio"
#include "math"




	static double GoolmathImpl::exp(double a){
		return math::exp(a);
	}


	static double GoolmathImpl::log(double a){
		return math::log(a);
	}

	static double GoolmathImpl::pow(double a , double b){
		return math::pow(a,b);
	}

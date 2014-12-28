Couchometer
===========

![Image](http://i.imgur.com/ELg33sj.png)

Meta-analyses of the effects of sitting on health are conclusive: sitting kills (c.f. http://www.popsci.com/science/gallery/2013-02/7-ways-sitting-will-kill-you/). 

However, apart from expensive accelerometer-clad wristbands (c.f. Fitbit), no simple app exists to track periods of inactivity and motivate one to stand up and move more often. Parsimony is our goal, and simpler solutions are often best. Hence: 

Couchometer is a simple single-use app designed to increase productivity through timed reminders to take active breaks throughout the day. This is accomplished through light machine learning and battery-conscious polling of accelerometer data.

## Motivation

I forced myself to study for the [Physics GRE](https://jaan.io/how-to-ace-the-gre-and-physics-gre) for strictly two hours a day. I would start a timer on my phone and pause it if I got a drink of water or stood up for whatever reason. 

This meant I was starting and stopping the timer a lot. It hit me that this would be simple to make an app out of: just start timing and pause the timer when the accelerometer on the phone is 'in motion'. 

A more complicated extension would be to classify sitting on a bus or car, when the user is sitting but the accelerometer is in motion (or detecting when the phone is not in someone's pocket, e.g. with light levels like what the Moto X does).

## To-do

* Fix 2 minute notification problem
* Fix pause/resume button
* Add bold font to title / custom graphics?
* Make sure it is able to track in the background
* Generate .apk and put on Google Play


Made with ♥ in a few short hours in Boston by Hoonie & Jaan. Many thanks to Maryse for the name!

(C) 2013-2014 Couchometer LLC (Limited Laziness Company).

# [FitNesse](http://docs.fitnesse.org/FrontPage) websocket plugin

![websocket](tests/FitNesseRoot/files/img/logo-websocket.png)

## Description
The Linky client project objectives are :
 * To be abble to receive Linky informations via [RS232](https://en.wikipedia.org/wiki/RS-232) interface,  according to [Enedis-NOI-CPT_54E](resources/Enedis-Linky-NOI-CPT_54E.pdf) specifications
 * To transmit raw informations to a web server via a [POST http request](https://en.wikipedia.org/wiki/POST_(HTTP)
 
## Hardware
 * An [arduino Uno](https://www.arduino.cc/en/Guide/ArduinoUno) 
 * An [Ethernet shield 2](https://www.arduino.cc/en/Guide/ArduinoEthernetShield) are used.
 * A [Custom hardware board](resources/domotique-info-schema.png)

## Software
 * Default IDE and toolchain are not used here.
 * Toolchain has been rewitten ([GNU make](https://www.gnu.org/software/make/manual/make.html)) to be able to 
    * build on multiple platforms (native, avr etc...)
    * compute code coverage with [lcov](https://wiki.documentfoundation.org/Development/Lcov) tool.
   
#### Dependencies
 * Compilators :
    * [gcc](https://gcc.gnu.org/) : x86 plateform
    * [avr-gcc](https://gcc.gnu.org/wiki/avr-gcc) : arduino platform

 * Tools :
    * [GNU make](https://www.gnu.org/software/make/manual/make.html)
    * [lcov](https://wiki.documentfoundation.org/Development/Lcov)

#### How to
 * build cross executable :
 
```shell_session
$ cd ./src/main
$ make build PLATFORM=cross CONFIGURATION=debug EXECUTABLE=linky
```
 * Download on arduino uno board:

You have to configure usb interface before, set 'AVRDUDE_PORT' macro in ./def_cross.mk.

```shell_session
$ cd ./src/main
$ make download
```

 * Build and run units tests (native):

```shell_session
$ cd ./tests/units_tests
$ make build run PLATFORM=native CONFIGURATION=debug

```

 * Build and run units tests with coverage:

```shell_session
$ cd ./tests/units_tests
$ make build run PLATFORM=native CONFIGURATION=debug COVERAGE=1
```

All artifacts are generated under ./build directory.

     
#### Configuration
 * followings [ git subrepo](https://github.com/ingydotnet/git-subrepo) are used
    * [ArduinoCore-avr](https://github.com/arduino/ArduinoCore-avr.git) : in directory arduino_core_avr
    * [Ethernet](https://github.com/arduino-libraries/Ethernet.git) : in directory arduino_libraries/ethernet
package parking

fun main() {
    val lot = MutableList(20) { "0" }
    lot.clear()
    while (true) {
        val command = readln().split(" ")
        if (command[0] == "create") {
            lot.clear()
            for (i in 1..command[1].toInt()) {
                lot.add("0")
            }
            println("Created a parking lot with ${command[1]} spots.")
        }
        if (command[0] == "exit") break
        if (lot.isNotEmpty()) {
            if (command[0] == "park" && "0" in lot) {
                var index = lot.indexOf("0")
                var car = mutableListOf(command[1] + " " + command[2]).joinToString(" ")
                lot.add(index, car)
                lot.removeAt(index + 1)
                println("${command[2]} car parked in spot ${lot.indexOf(car) + 1}.")
            } else if (command[0] == "park" && "0" !in lot) {
                println("Sorry, the parking lot is full.")
            }
            if (command[0] == "leave") {
                lot.removeAt(command[1].toInt() - 1)
                lot.add(command[1].toInt() - 1, "0")
                println("Spot ${command[1].toInt()} is free.")
            }
            if (command[0] == "status" && lot.sortedDescending()[0] != "0") {
                for (i in 0 until lot.size) {
                    if (lot[i] != "0") {
                        println("${i + 1} ${lot[i]}")
                    }
                }
            } else if (command[0] == "status" && lot.sortedDescending()[0] == "0") {
                println("Parking lot is empty.")
            }
            if (command[0] == "reg_by_color") {
                var info = mutableListOf<String>()
                for (i in 0 until lot.size) {
                    if (command[1].uppercase() in lot[i].uppercase()) {
                        val reg = lot[i].split(" ")
                        info.add(reg[0])
                    }
                }
                if (info.isNotEmpty()) {
                    println(info.joinToString())
                } else {
                    println("No cars with color ${command[1]} were found.")
                }
            }
            if (command[0] == "spot_by_color") {
                var info = mutableListOf<Int>()
                for (i in 0 until lot.size) {
                    if (command[1].uppercase() in lot[i].uppercase()) {
                        info.add(i + 1)
                    }
                }
                if (info.isNotEmpty()) {
                    println(info.joinToString())
                } else {
                    println("No cars with color ${command[1]} were found.")
                }
            }
            if (command[0] == "spot_by_reg") {
                var info = mutableListOf<Int>()
                for (i in 0 until lot.size) {
                    val reg = lot[i].split(" ")
                    if (command[1] == reg[0]) {
                        info.add(i + 1)
                    }
                }
                if (info.isNotEmpty()) {
                    println(info.joinToString())
                } else {
                    println("No cars with registration number ${command[1]} were found.")
                }
            }
        } else {
            println("Sorry, a parking lot has not been created.")
        }
    }
}

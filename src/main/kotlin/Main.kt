package de.griefed

fun main(args: Array<String>) {
    val lottoNumberUtils = LottoNumberUtils()
    val site = "https://www.lottozahlenonline.de/statistik/beide-spieltage/lottozahlen-auflistung.php"
    val combinations = lottoNumberUtils.extractCombinations(site)

    println("Previous combinations:")
    for (combination in combinations) {
        println(combination)
    }

    println("Considerable new combination(s):")
    val newCombinations = if (args.size == 1) {
        lottoNumberUtils.generateNewCombinations(combinations, args[0].toInt())
    } else {
        lottoNumberUtils.generateNewCombinations(combinations)
    }
    for (combination in newCombinations) {
        println(combination)
    }
}
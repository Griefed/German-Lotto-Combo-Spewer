package de.griefed

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException
import java.net.URI

@Suppress("RedundantExplicitType")
class LottoNumberUtils {
    val validNumbers: List<Int> = listOf(
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        13,
        14,
        15,
        16,
        17,
        18,
        19,
        20,
        21,
        22,
        23,
        24,
        25,
        26,
        27,
        28,
        29,
        30,
        31,
        32,
        33,
        34,
        35,
        36,
        3,
        38,
        39,
        40,
        41,
        42,
        43,
        44,
        45,
        46,
        47,
        48,
        49
    )

    @Throws(IOException::class)
    fun extractCombinations(url: String): List<List<Int>> {

        val baseURL: String = "https://${URI(url).host}"
        val doc: Document = Jsoup.connect(url).get()

        val links: List<String> = doc
            .select("div.archiv_flex_item > a")
            .map { e -> "$baseURL${e.attr("href")}" }

        val rows: MutableList<Element> = mutableListOf()

        for (link in links) {
            val rowPage: Document = Jsoup.connect(link).get()
            val rowsOfYear: Elements = rowPage.select("div.tab_overflow > div.rahmen1")
            rowsOfYear.forEach { row -> rows.add(row) }
        }

        val combinations: MutableList<MutableList<Int>> = mutableListOf()

        for (row in rows) {
            val numbers: MutableList<Int> = mutableListOf()
            val entries: Elements = row.select("div.quadrat2")
            for (element in entries) {
                numbers.add(element.text().toInt())
            }
            combinations.add(numbers)
        }

        return combinations
    }

    fun generateNewCombinations(knownCombinations: List<List<Int>>, amount: Int = 1): List<List<Int>> {
        val newCombinations: MutableList<List<Int>> = mutableListOf()

        for (i in 1..amount) {
            val usedNumbers: MutableList<Int> = mutableListOf()
            val newCombination: MutableList<Int> = mutableListOf()
            do {
                usedNumbers.clear()
                newCombination.clear()
                for (n in 0..5) {
                    var randomNumber: Int
                    do {
                        randomNumber = validNumbers.random()
                    } while (usedNumbers.contains(randomNumber))
                    usedNumbers.add(randomNumber)
                    newCombination.add(randomNumber)
                }
            } while(knownCombinations.contains(newCombination) || newCombinations.contains(newCombination))

            newCombinations.add(newCombination)
        }

        return newCombinations
    }
}
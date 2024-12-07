package com.example.androidhomeworks

class NumberToTextConverter {

    fun convertNumberToGeorgianText(number: Int): String {
        var num = number
        val units = arrayOf("ერთი", "ორი", "სამი", "ოთხი", "ხუთი", "ექვსი", "შვიდი", "რვა", "ცხრა")
        val teens = arrayOf("ათი", "თერთმეტი", "თორმეტი", "ცამეტი", "თოთხმეტი", "თხუთმეტი", "თექვსმეტი", "ჩვიდმეტი", "თვრამეტი", "ცხრამეტი")
        val exactTens = arrayOf("ათი", "ოცი", "ოცდაათი", "ორმოცი", "ორმოცდაათი", "სამოცი", "სამოცდაათი", "ოთხმოცი", "ოთხმოცდაათი")
        val tens = arrayOf("ოცდა","ორმოცდა","სამოცდა","ოთხმოცდა")
        val hundreds = arrayOf("ას", "ორას", "სამას", "ოთხას", "ხუთას", "ექვსას", "შვიდას", "რვაას", "ცხრაას")
        var result = ""
        if (num == 1000){
            return "ათასი"
        }
        if (num >=100){
            result = hundreds[num/100 - 1]
            num %= 100
            if (num == 0){
                return result  + "ი"
            }
        }
        if (num%10 == 0){
            result += exactTens[num/10 -1]
            return result
        }
        else if(num > 20){
            result += tens[num/20 - 1]
            if (num.firstDigit() % 2 == 1){
                result += teens[num%10]
                return result
            }
            num %= 10
        }
        if (num >= 10) {
            result += teens[num - 10]
        }
        else if (num > 0) {
            result += units[num - 1]
        }

        return result
    }

    fun convertNumberToEnglishText(number:Int): String {
        var num = number
        val units = arrayOf("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
        val teens = arrayOf("Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen")
        val tens = arrayOf("Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")
        var result = ""
        if (num == 1000){
            return "One Thousand"
        }
        if (num >=100){
            result = units[num/100 - 1] + " Hundred "
            num %= 100
            if (num== 0){
                return result
            }
        }
        if (num%10 == 0){
            result += tens[num/10 -1]
            return result
        }
        else if(num > 20){
            result += tens[num/10 - 1] + "-"
            num %= 10
        }
        if (num >= 10) {
            result += teens[num - 10]
        }
        else if (num > 0) {
            result += units[num - 1]
        }

        return result
     }
}
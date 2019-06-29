@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import kotlin.coroutines.experimental.coroutineContext
import kotlin.math.*

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val string = age.toString()
    val digits = string.map(Character::getNumericValue).toIntArray()
    var agePhrase = ""
    if (age<=9) {
        when (digits[0]) {
            1 -> agePhrase = "$age год"
            2, 3, 4 -> {
                agePhrase = "$age года"
            }
            5, 6, 7, 8, 9, 0 -> {
                agePhrase = "$age лет"
            }
        }
    }
    if (age>=10 && age<=20) {agePhrase = "$age лет"}
    if (age>=110 && age<120) {agePhrase = "$age лет"}

    if (age>20 && age<100) {
        when (digits[1]) {
            1 -> agePhrase = "$age год"
            2, 3, 4 -> {
                agePhrase = "$age года"
            }
            5, 6, 7, 8, 9, 0 -> {
                agePhrase = "$age лет"
            }
        }
    }
    if (age>=100 && age<110) {
        when (digits[2]) {
            1 -> agePhrase = "$age год"
            2, 3, 4 -> {
                agePhrase = "$age года"
            }
            5, 6, 7, 8, 9, 0 -> {
                agePhrase = "$age лет"
            }
        }
    }
    if (age>=120) {
        when (digits[2]) {
            1 -> agePhrase = "$age год"
            2, 3, 4 -> {
                agePhrase = "$age года"
            }
            5, 6, 7, 8, 9, 0 -> {
                agePhrase = "$age лет"
            }
        }
    }
    return agePhrase
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    var timeForHalfWay = 0.0
    //if ((t1<0)||(t2<0)||(t3<0)||(v1<0)||(v2<0)||(v3<0)) timeForHalfWay = 0.0
    val s1 = t1*v1
    val s2 = t2*v2
    val s3 = t3*v3
    val sTotal = s1 + s2 + s3
    val sHalf = sTotal/2
    if (s1==sHalf) {
        timeForHalfWay = t1
    }
    if (s1+s2==sHalf) {
        timeForHalfWay = t1 + t2
    }
    if (s1>sHalf) {
        timeForHalfWay = t1 - (s1-sHalf)/v1
    }
    if ((s1<sHalf) && (sHalf<s1+s2)) {
        timeForHalfWay = t1 + (s2 + s3 - sHalf)/v2
    }
    if (s3>sHalf) {
        timeForHalfWay = t1 + t2 + (s3-sHalf)/v3
    }

    return timeForHalfWay

}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int = TODO()

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int = TODO()

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var kind = 3
    fun sqr(x: Double) = x*x
    var maxSide = 0.0
    var secondSide = 0.0
    var thirdSide = 0.0
    if ((a>=b)&&(a>=c)){
        maxSide = a
        secondSide = b
        thirdSide = c
    }
    if ((b>=a)&&(b>=c)){
        maxSide = b
        secondSide = a
        thirdSide = c
    }
    if ((c>=a)&&(c>=b)){
        maxSide = c
        secondSide = a
        thirdSide = b
    }
   /* if ((a==b)||(a==c)||(b==c)) {
        maxSide = a
        secondSide = b
        thirdSide = c
    }
    */

    if ((a+b>c)&&(a+c>b)&&(b+c>a)&&(a>0)&&(b>0)&&(c>0)) {

        if ((sqr(maxSide))<(sqr(secondSide)+sqr(thirdSide))) kind = 0
        if ((sqr(maxSide))==(sqr(secondSide)+sqr(thirdSide))) kind = 1
        if ((sqr(maxSide))>(sqr(secondSide)+sqr(thirdSide))) kind = 2
    }
    else
    {
        kind = -1
    }
    return kind
}



/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {

    var segLength = 0

// check intersection, if none, then return -1
    if ((b<a)||(d<c)) {
        return -1
    }
    if ((d<a)) {
        return -1
    }

// check if segments are the same
    if ((b - a) == (d - c)){
        segLength = b - a
    }
// check if ab is smaller than ad
    if ((b>c)||(b<d)){
        segLength = b - c
    }
// check if ab has cd inside
    if (b>d) {
        segLength = d - c
    }
// check if cd has ab inside
    if ((c<a) && (d>b)) {
        segLength = b - a
    }
// check if ad is inside cb
    if ((c<a) && (d<b)) {
        segLength = d - a
    }

    return segLength
}

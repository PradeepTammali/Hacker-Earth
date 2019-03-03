var regExVowels = /[aeiou]/ig;
var regExConsonents = /[aeiouy]/ig;
var startsWith = /^[!@#$%^&*(),.?":{}|<>\[\];'0-9]+/g;
var endsWith = /[!@#$%^&*(),.?":{}\\|<>/\[\];0-9']+$/g;
var paragraphSplit = /\b(\w+\W+)/g;
var isUpper = /^[A-Z]/;



var Hypher = require('hypher'),
    english = require('hyphenation.en-us'),
    h = new Hypher(english);


function convertToPigLatin(paragraph) {
    encodedParagraph = '';

    // Splitting paragraph with paragraphSplit regEx but not woking fine with URLs 
    paragraph.split(" ").forEach(string => {
        var encodedString = '';
        var stringEndingChars = '';
        var stringStartingChars = '';
        var vowelsSuffix = 'way';
        var consonantsSuffix = 'ay';
        var compoundWords = string.split("-");
        // if (compoundWords.length > 1) {
        //     var encodedCompoundString = "";
        //     compoundWords.forEach(word => {
        //         encodedCompoundString += convertToPigLatin(word).trim() + "-";
        //     });
            // Checking if the string is starting with special characters or numbers
            if (string.match(startsWith)) {
                var startIndex = string.search(/[a-zA-Z]/g);
                stringStartingChars = string.substring(0, startIndex);
                string = string.substring(startIndex, string.length)
            }
            // Checking if the string ending with special character or numbers 
            if (string.match(endsWith)) {
                var endsIndex = string.search(endsWith);
                stringEndingChars = string.substring(endsIndex, string.length);
                string = string.substring(0, endsIndex)
            }
            // Encoding the string 
            if (string != null && string != undefined && string != '') {
                var isCapitalized = false;
                // Capitalization flag
                if (isUpper.test(string[0])) {
                    isCapitalized = true;
                }
                // Encoding for vowels 
                if (string[0].match(regExVowels)) {
                    lastChar = string.charAt(string.length - 1)
                    if (lastChar == lastChar.toUpperCase() && string.length > 1) {
                        vowelsSuffix = vowelsSuffix.toUpperCase();
                    }
                    encodedString = string + vowelsSuffix;
                } else {
                    if (string == string.toUpperCase()) {
                        isCapitalized = false;
                        consonantsSuffix = consonantsSuffix.toUpperCase();
                    }
                    var index = string.search(regExConsonents)
                    // Encoding consonants with y condition 
                    if (index == 0 && (string[index] == 'y' || string[index] == 'Y')) {
                        encodedString = string.substring(1, string.length) + string[0].toLowerCase() + consonantsSuffix;
                    } else {
                        // Encoding consonants 
                        if (isCapitalized) {
                            encodedString = string.substring(index, string.length) + string.charAt(0).toLowerCase() + string.substring(1, index) + consonantsSuffix;
                        } else {
                            encodedString = string.substring(index, string.length) + string.substring(0, index) + consonantsSuffix;
                        }
                    }
                }
            }
            // Reformatting the string 
            if (isCapitalized) {
                encodedParagraph += stringStartingChars + encodedString[0].toUpperCase() + encodedString.substring(1, encodedString.length) + stringEndingChars + " ";
            } else {
                encodedParagraph += stringStartingChars + encodedString + stringEndingChars + " ";
            }
    });
    return encodedParagraph;
}

var str = "This eBook is for the use of anyone anywhere in the United States and most \
other parts of the world at no cost and with almost no restrictions \
whatsoever.  You may copy it, give it away or re-use it under the terms of \
the Project Gutenberg License included with this eBook or online at \
www.gutenberg.org.  If you are not located in the United States, you'll have \
to check the laws of the country where you are located before using this ebook."
console.log(convertToPigLatin("Re-Run"));
// console.log(compoundWords("re-use"))
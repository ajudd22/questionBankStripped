var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab


function displayTest(test){
  console.log("Here's your test!");
  console.log(test);
  var jsonstring = test.response;
  var testObj = JSON.parse(jsonstring);
  var s = "<div class='test-container'>";
  console.log(testObj);
  for (let i = 0; i < testObj.Test.length; i ++){
    s += "<div class = 'test-question-container'>";
    s += "<h4 class='question-head'>"+ testObj.Test[i].head + "</h4>";
    s += "<input type='radio' class='option correct' id='"+ i +"correct' name = '"+ i +"q'><label class = 'test-option' for= '"+ i +"correct'>"+ testObj.Test[i].c +"</label><br>";
    s += "<input type='radio' class='option incorrect' id = '"+ i +"incor1' name = '"+ i +"q'><label class = 'test-option' for= '"+ i +"incor1'>"+ testObj.Test[i].op1 +"</label><br>";
    s += "<input type='radio' class='option incorrect' id = '"+ i +"incor2' name = '"+ i +"q'><label class = 'test-option' for= '"+ i +"incor2'>"+ testObj.Test[i].op2 +"</label><br>";
    s += "<input type='radio' class='option incorrect' id = '"+ i +"incor3' name = '"+ i +"q'><label class = 'test-option' for= '"+ i +"incor3'>"+ testObj.Test[i].op3 +"</label><br>";
    s += "</div>";
  }
  s += "</div>";
  document.getElementById('testContainer').innerHTML = s;
  document.getElementsByClassName('content')[0].style.flexDirection = "column";
}
function requestTest(){
  var books = Array.from(document.getElementsByClassName('book-of-scripture-button'));
  var chs = Array.from(document.getElementsByClassName('chapter-button'));
  var checkedbooks = books.filter(function(book){
    return book.checked;
  });
  var checkedchaps = chs.filter(function(ch){
    return ch.checked;
  });
  var hashMap = checkedbooks.map(function(checkedbook){
    var bookName = checkedbook.labels[0].innerText;

    var formBooks = ["1 Nephi", "2 Nephi", "Jacob", "Enos", "Jarom", "Omni", "Words Of Mormon", "Alma"
    , "Helaman", "3 Nephi", "4 Nephi", "Mormon", "Ether", "Moroni"];

    var enumbooks= ["NEPHI1" , "NEPHI2", "JACOB", "ENOS", "JAROM", "OMNI",
    "WORDSOFMORMON", "MOSIAH", "ALMA", "HELAMAN", "NEPHI3", "NEPHI4", "MORMON", "ETHER", "MORONI"]

    var enumName = enumbooks[formBooks.indexOf(bookName)];
    var chapters = [];
    for (let i = 0; i <checkedchaps.length; i++)
    {
      var str = checkedchaps[i].labels[0].htmlFor;
      if (str.slice(0, bookName.length) == bookName){
        chapters.push(parseInt(checkedchaps[i].labels[0].innerText));
      }
    }
    return {book:enumName, chapters: chapters};
  })
  var difficulties = Array.from(document.getElementsByClassName('difficulty-button'));
  var difficulty = difficulties.filter(function(diffs){
    return diffs.checked;
  })
  difficulty = difficulty[0].id[0];

  closedbook = document.getElementById('closedButton').checked;

  length = document.getElementById('lengthInput').value;
  if (length < 20) length = 20;
  if (length > 60) length = 60;

  var method = "POST";
  const path = "/createTest";
  var formdata = {'sections': hashMap, 'difficulty': parseInt(difficulty),
  'closedBook': closedbook, 'length': parseInt(length), 'assortment': 0};
  var formstring = JSON.stringify(formdata);
  var obj = new XMLHttpRequest();
  obj.onreadystatechange = function (){
     if (obj.readyState == 4) displayTest(obj);
  }

  obj.open(method,path, true);
  obj.send(formstring);
  console.log("sent");
  return false;
}
function submitForm(){

  var buttons = document.getElementsByClassName('book-of-scripture-button');
  var clicked = [];
  var i;
  for (i = 0; i <buttons.length; i++){
    if (buttons[i].checked == true)
    {
      clicked.push(buttons[i]);//create array of books selected
    }
  }
  var chapters = document.getElementsByClassName("chapter-button")
  clicked = [];
  for (let i = 0; i < chapters.length; i++){
    if (chapters[i].checked == true)
    {
      clicked.push(chapters[i]);//create array of books selected
    }
  }
  var x =document.getElementsByClassName('next-prev-container');
  x[0].style.display = "none";
  requestTest();
}


function displayChapters(){
  var buttons = document.getElementsByClassName('book-of-scripture-button');
  var clicked = [];
  var i;
  for (i = 0; i <buttons.length; i++){
    if (buttons[i].checked == true)
    {
      clicked.push(buttons[i]);//create array of books selected
    }
  }
  var books = [
    {
      book: "1 Nephi",
      chapters: 22
    },
    {
      book: "2 Nephi",
      chapters: 33
    },
    {
      book: "Jacob",
      chapters: 7
    },
    {
      book: "Enos",
      chapters: 1
    },
    {
      book: "Jarom",
      chapters: 1
    },
    {
      book: "Omni",
      chapters: 1
    },
    {
      book: "Words of Mormon",
      chapters: 1
    },
    {
      book: "Mosiah",
      chapters: 29
    },
    {
      book: "Alma",
      chapters: 63
    },
    {
      book: "Helaman",
      chapters: 16
    },
    {
      book: "3 Nephi",
      chapters: 30
    },
    {
      book: "4 Nephi",
      chapters: 1
    },
    {
      book: "Mormon",
      chapters: 9
    },
    {
      book: "Ether",
      chapters: 15
    },
    {
      book: "Moroni",
      chapters: 10
    },

  ]
  var s = "";
  for (let i = 0; i <clicked.length; i++)
  {
    var name = clicked[i].nextSibling.innerText;
    s += "<h6 class='book-label'>"+name+"</h6><div class='chapter-label-container'>";
    var chapters = books.find(obj => {return obj.book === name}).chapters

    for (let j = 1; j < chapters+1; j++)
    {

      s +="<input class = 'chapter-button' type = 'checkbox' id='"+name+j+"Button'><label class= 'chapter-label' for='"+name+j+"Button'>"+j+"</label>";
    }
  }
  s += "</div>"

  if (s === "</div>")return;
  document.getElementById("chaptersList").innerHTML = s;
}


//-----------ACCORDION DISPLAY CODE--------------------
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].onclick = function() {
    var active = document.querySelector(".accordion.active");
    if (active && active != this) {
      active.classList.remove("active");
      active.nextElementSibling.classList.remove("show");
    }
    this.classList.toggle("active");
    this.nextElementSibling.classList.toggle("show");
  }
}
//---------FORM DISPLAY CODE----------
function fixStepIndicator(n) {  // This function removes the "active" class of all steps...
  var i, x = document.getElementsByClassName("step");
  for (i = 0; i < x.length; i++) {
    x[i].className = x[i].className.replace(" active", "");
  }
  x[n].className += " active";  //... and adds the "active" class to the current step:
}
function nextPrev(n) {  // This function will figure out which tab to display
  var x = document.getElementsByClassName("tab");
  x[currentTab].style.display = "none";  // Hide the current tab:
  currentTab = currentTab + n;  // Increase or decrease the current tab by 1:
  if (currentTab ==1){displayChapters();}
  if (currentTab >= x.length) {  // if you have reached the end of the form... :
    if(validateForm()==true){submitForm();}
    return false;
  }
  showTab(currentTab);  // Otherwise, display the correct tab:
}
function showTab(n) {  // This function will display the specified tab of the form ...
  var x = document.getElementsByClassName("tab");
  x[n].style.display = "block";  // ... and fix the Previous/Next buttons:
  if (n == 0) {
    document.getElementById("prevBtn").style.display = "none";
  } else {
    document.getElementById("prevBtn").style.display = "inline";
  }
  if (n == (x.length - 1)) {
    document.getElementById("nextBtn").innerHTML = "Submit";
  } else {
    document.getElementById("nextBtn").innerHTML = "Next";
  }
  fixStepIndicator(n)  // ... and run a function that displays the correct step indicator:
}
function validateForm() {
  var buttons = document.getElementsByClassName('book-of-scripture-button');
  var clicked = [];
  var i;
  for (i = 0; i <buttons.length; i++){
    if (buttons[i].checked == true)
    {
      clicked.push(buttons[i]);//create array of books selected
    }
  }
  if (clicked.length === 0){return false;}

  var chapters = document.getElementsByClassName('chapter-button');
  clicked = [];
  for (let i = 0; i <chapters.length; i++){
    if (chapters[i].checked == true)
    {
      clicked.push(buttons[i]);//create array of books selected
    }
  }
  if (clicked === 0){return false;}

  return true; // return the valid status
}

//javascript is going to create a request object and send it

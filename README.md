Dine In! App
This app helps the managerial staff of a restaurant to keep track of items ordered at various tables and generate bills when someone has finished dining, all with the simplicity of using a smartphone, and not the various desktop applications needed to do so otherwise.

This can make the dining experience much more seamless by eliminating the need to use desktops in dine in restaurants. 

Functionality that can be added includes a button that links to a payment gateway, generation of a printable/sendable copy of the bill in the form of a document.

Technologies used: Android Studio, Kotlin, XML

How to Run:
1. you must have Android Studio installed on your PC, and an Android smartphone to run the app.
2. download the code in this repository as a zip file and extract its contents.
3. Open Android Studio and click on the File->Open option.
4. locate the DineIn contents and select them, click on open.
5. Wait for the project to build.
6. Plug-in your android device to the computer, and select the device in the list right next to the run app button.
7. Click on the run app button.

Layout:
1. Start/Welcome fragment: The first screen that is displayed when you open the app.
2. Item List fragment : This screen shows all the items ordered at all the tables, with the quantity and price of each individual item.
3. Item Detail fragment : When you click on an item name from the list, it loads in this fragment. There is an edit button, which leads us to 
   the Add/Edit fragment and there we can update a record or add a new one.
4. Add / Edit fragment : When you click the add item button in the item list fragment, it loads this fragment to add a new item (all entries are blank), 
   but when you click the edit button in the item detail fragment,  it leads us to this fragment with all columns having the data of the item that 
   was selected, and we can change whatever we like.
5. Table for Billing fragment : in the item list fragment, if we click on the generate bill button, this screen loads. Here you enter the table number 
   for which we want the bill to be generated, which leads us to the next screen that displays all the items ordered at the table and the total amount.
7. Billing fragment: Here, all the items ordered at the table are displayed here along with the final amount.

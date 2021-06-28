import 'dart:io';

import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
        // This makes the visual density adapt to the platform that you run
        // the app on. For desktop platforms, the controls will be smaller and
        // closer together (more dense) than on mobile platforms.
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home:LocalDatabaseExample(),
    );
  }
}

class LocalDatabaseExample extends StatefulWidget {
  @override
  _LocalDatabaseExampleState createState() => _LocalDatabaseExampleState();
}

class _LocalDatabaseExampleState extends State<LocalDatabaseExample> {
String data;
  Future<String> get _localpath  async{
    final directory = await getApplicationDocumentsDirectory();
    print(directory.path);
    return directory.path;
  }

  Future<File> get _localfile async{
    final path = await _localpath;
    return File('$path/data.txt');
  }

  Future<File> writeContent() async{
    final file = await _localfile;
    return file.writeAsString('Helloe this ifirst');
  }

  Future<String> readcontent() async{
    try{
      final file =  await _localfile;
      String content = await file.readAsString();
      return content;

    }catch(e)
    {
            return 'errror';
    }
  }
  @override
  void initState() {
    super.initState();
    writeContent();
    readcontent().then((String value){
      setState(() {

        data = value;
      });
    });

  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
title: Text("Reading and Writing Data"),
        centerTitle: true,
      ),
      body: Center(
        child: Text("Data read from firle $data"),
      ),
    );
  }
}

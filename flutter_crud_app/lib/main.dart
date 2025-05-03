import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Firebase CRUD',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final CollectionReference _firestoreRef =
      FirebaseFirestore.instance.collection("empleados");

  final TextEditingController _nombreController = TextEditingController();
  final TextEditingController _cargoController = TextEditingController();

  String? _selectedDocId; 

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Sistema CRUD de Empleados (Firestore)"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _nombreController,
              decoration: InputDecoration(labelText: "Nombre"),
            ),
            TextField(
              controller: _cargoController,
              decoration: InputDecoration(labelText: "Cargo"),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                if (_selectedDocId == null) {
                  _crearEmpleado();
                } else {
                  _actualizarEmpleado();
                }
              },
              child: Text(_selectedDocId == null ? "Guardar" : "Actualizar"),
            ),
            SizedBox(height: 20),
            Expanded(
              child: StreamBuilder<QuerySnapshot>(
                stream: _firestoreRef.snapshots(),
                builder: (context, snapshot) {
                  if (snapshot.hasError) {
                    return Center(child: Text("Error: ${snapshot.error}"));
                  }

                  if (snapshot.connectionState == ConnectionState.waiting) {
                    return Center(child: CircularProgressIndicator());
                  }

                  final empleados = snapshot.data!.docs;

                  if (empleados.isEmpty) {
                    return Center(child: Text("No hay empleados registrados"));
                  }

                  return ListView.builder(
                    itemCount: empleados.length,
                    itemBuilder: (context, index) {
                      final doc = empleados[index];
                      final data = doc.data() as Map<String, dynamic>;

                      return ListTile(
                        title: Text(data["nombre"] ?? "Sin nombre"),
                        subtitle: Text(data["cargo"] ?? "Sin cargo"),
                        trailing: Row(
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            IconButton(
                              icon: Icon(Icons.edit, color: Colors.green),
                              onPressed: () {
                                _nombreController.text = data["nombre"];
                                _cargoController.text = data["cargo"];
                                setState(() {
                                  _selectedDocId = doc.id;
                                });
                              },
                            ),
                            IconButton(
                              icon: Icon(Icons.delete, color: Colors.red),
                              onPressed: () {
                                _eliminarEmpleado(doc.id);
                              },
                            ),
                          ],
                        ),
                      );
                    },
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }

  void _crearEmpleado() {
    String nombre = _nombreController.text.trim();
    String cargo = _cargoController.text.trim();

    _firestoreRef.add({
      "nombre": nombre,
      "cargo": cargo,
    });

    _nombreController.clear();
    _cargoController.clear();
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text("Empleado registrado exitosamente"))
    );
  }

  void _actualizarEmpleado() {
    String nombre = _nombreController.text.trim();
    String cargo = _cargoController.text.trim();

    _firestoreRef.doc(_selectedDocId!).update({
      "nombre": nombre,
      "cargo": cargo,
    });

    _nombreController.clear();
    _cargoController.clear();
    setState(() {
      _selectedDocId = null;
    });

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text("Empleado actualizado exitosamente"))
    );
  }

  void _eliminarEmpleado(String docId) {
    _firestoreRef.doc(docId).delete();

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text("Empleado eliminado"))
    );
  }
}

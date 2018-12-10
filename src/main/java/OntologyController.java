package main.java;

import org.semanticweb.HermiT.Configuration;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.*;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class OntologyController {

    OWLDataFactory factory;
    OWLOntology inferredOntology;
    OWLOntology ontology;
    OWLOntologyManager manager;
    OWLReasonerFactory reasonerFactory;
    OWLReasoner inferredReasoner;
    OWLReasoner reasoner;
    static String inferredOntologyPath="source/ontologies/conditerOntologies/infOntConditer.owl";
    static String ontologyPath="source/ontologies/conditerOntologies/lab3conditer1.owl";
    
    ArrayList<String> listError = new ArrayList<String>();

    public void init() {
        File inferredOntologyFile = new File(inferredOntologyPath);
        if(!inferredOntologyFile.exists()) {
            try {
                initReasoner();
            } catch (Exception e) {
               
            } 
        }

        try {
           initOntology();
        }catch (Exception e) {
            
        }
    }

    private void initOntology() throws Exception {
    	 manager = OWLManager.createOWLOntologyManager();
         factory = manager.getOWLDataFactory();
         reasonerFactory = new StructuralReasonerFactory();

         File inferredOntologyFile = new File(inferredOntologyPath);
         File ontologyFile = new File(ontologyPath);
         inferredOntology = manager.loadOntologyFromOntologyDocument(inferredOntologyFile);
         inferredReasoner = reasonerFactory.createReasoner(inferredOntology);  
         ontology = manager.loadOntologyFromOntologyDocument(ontologyFile);
         reasoner = reasonerFactory.createReasoner(ontology);
		
	}

	public ArrayList<String> getInstance(String s) {
        ArrayList<String> list = new ArrayList<String>();
        for (OWLClass cls : inferredOntology.getClassesInSignature()) {
            if (cls.getIRI().getFragment().equals(s)){
                NodeSet<OWLNamedIndividual> instances = inferredReasoner.getInstances(cls, true);
                for (OWLNamedIndividual i : instances.getFlattened()) {
                    list.add(i.getIRI().getFragment());
                }
            }
        }
        return list;
    }

    public static void initReasoner() throws Exception {

        OWLOntologyManager manager=OWLManager.createOWLOntologyManager();
        File inputOntologyFile = new File(ontologyPath);
        OWLOntology ontology=manager.loadOntologyFromOntologyDocument(inputOntologyFile);
        Reasoner.ReasonerFactory factory = new Reasoner.ReasonerFactory();
        Configuration config=new Configuration();
        config.reasonerProgressMonitor=new ConsoleProgressMonitor();
        OWLReasoner reasoner=factory.createReasoner(ontology, config);
        List<InferredAxiomGenerator<? extends OWLAxiom>> axiomGenerators=new ArrayList<InferredAxiomGenerator<? extends OWLAxiom>>();
        axiomGenerators.add(new InferredSubClassAxiomGenerator());
        axiomGenerators.add(new InferredClassAssertionAxiomGenerator());
        axiomGenerators.add(new InferredDisjointClassesAxiomGenerator() {
            boolean precomputed=false;
            protected void addAxioms(OWLClass entity, OWLReasoner reasoner, OWLDataFactory dataFactory, Set<OWLDisjointClassesAxiom> result) {
                if (!precomputed) {
                    reasoner.precomputeInferences(InferenceType.DISJOINT_CLASSES);
                    precomputed=true;
                }
                for (OWLClass cls : reasoner.getDisjointClasses(entity).getFlattened()) {
                    result.add(dataFactory.getOWLDisjointClassesAxiom(entity, cls));
                }
            }
        });
        InferredOntologyGenerator inferredOntologyGenerators=new InferredOntologyGenerator(reasoner,axiomGenerators);
        OWLOntology inferredAxiomsOntology=manager.createOntology();
        inferredOntologyGenerators.fillOntology( manager, inferredAxiomsOntology);
        File inferredOntologyFile=new File(inferredOntologyPath);
        inferredOntologyFile=inferredOntologyFile.getAbsoluteFile();
        OutputStream outputStream=new FileOutputStream(inferredOntologyFile);
        manager.saveOntology(inferredAxiomsOntology, manager.getOntologyFormat(ontology), outputStream);
    }

	public ArrayList<String> getAllProducts() {
		ArrayList <String> products=new ArrayList<String>();
		for (OWLNamedIndividual product : ontology.getIndividualsInSignature()) {
			String productName=product.toString().split("#")[1].split(">")[0];
			products.add(productName);
		}
		return products;
	}
	
	public ArrayList <String> getProductDataValues(String productName) {
		ArrayList <String> productDataValues=new ArrayList<String>();
		for (OWLNamedIndividual product : ontology.getIndividualsInSignature()) {
			String productNameStr=product.toString().split("#")[1].split(">")[0];
			if(productNameStr.equals(productName))
				productDataValues.addAll(getDataValues(product.getDataPropertyValues(ontology).toString().split("#")));
			//System.out.println("product: "+product.getDataPropertyValues(localVel));
		}
		//System.out.println("product: "+productDataValues);
		getComposition(productName);
		return productDataValues;
	}
	private ArrayList <String> getDataValues(String[]parsedValues) {
		ArrayList <String> dataValues=new ArrayList<String>();
		for(String value : parsedValues) {
			if(value.contains(">")) {
			String valueName=value.split(">")[0];
			String valueValue=value.split("\"")[1].split("\"")[0];
			dataValues.add(valueName+": "+valueValue);
			}
		}
		return dataValues;
	}
	
	public ArrayList <String> getComposition(String productName) {
		ArrayList <String> productComposition=new ArrayList<String>();
		String className="";
		if(productName.contains("СладкО"))
			className=productName.split("СладкО")[0];
		else
			className=productName.split("ОтКондитерской")[0];
		for (OWLClass cls : ontology.getClassesInSignature()) {
			String productNameStr=cls.toString().split("#")[1].split(">")[0];
			if(productNameStr.equals(className))
				//productDataValues.addAll(getDataValues(product.getDataPropertyValues(localVel).toString().split("#")));
				productComposition.addAll(getCompositionValues(cls.getReferencingAxioms(ontology).toString().split("ObjectIntersectionOf")));
		}
		//System.out.println("product: "+productDataValues);
		return productComposition;	
	}
	private ArrayList <String> getCompositionValues(String[]parsedValues) {
		ArrayList <String> compositionValues=new ArrayList<String>();
		for(String value : parsedValues) {
			if(value.contains("ObjectSomeValuesFrom")) {
				for(String objValue : value.split("ObjectSomeValuesFrom")) {
					//System.out.println("composition value: "+objValue);
					if(objValue.contains("#")) {
					String constraintName=objValue.split("#")[1].split(">")[0];
					String ingredientValue=objValue.split("#")[2].split(">")[0];
					compositionValues.add(constraintName+": "+ingredientValue);
					}
				}
			}
		}
		return compositionValues;
	}
}

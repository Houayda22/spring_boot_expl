package com.example.graphqlmongodb.config;
// Importations nécessaires
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.File;
import java.io.IOException;

@Configuration
public class GraphQLConfig {

    // Bean pour GraphQL
    @Bean
    public GraphQL graphQL(GraphQLSchema graphQLSchema) {
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    // Bean pour GraphQLSchema
    @Bean
    public GraphQLSchema graphQLSchema() throws IOException {
        // Lire le schéma GraphQL à partir d'un fichier (par exemple, schema.graphqls)
        File schemaFile = new File("src/main/resources/schema.graphqls");
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);

        // Créer un RuntimeWiring pour lier les resolvers aux types
        RuntimeWiring wiring = buildRuntimeWiring();

        // Générer le schéma GraphQL
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, wiring);
    }

    // Méthode pour construire le RuntimeWiring
    private RuntimeWiring buildRuntimeWiring() {
        // Implémentez ici la logique pour lier les resolvers aux types définis dans votre schéma
        return RuntimeWiring.newRuntimeWiring().build();
    }
}

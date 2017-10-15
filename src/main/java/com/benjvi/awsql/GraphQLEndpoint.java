package com.benjvi.awsql;

import com.benjvi.awsql.repositories.AwsRepository;
import com.coxautodev.graphql.tools.SchemaParser;
import javax.servlet.annotation.WebServlet;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

/**
 * Created by benjamin on 20/09/2017.
 */

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private static final AwsRepository awsRepository;

    static {
        awsRepository = new AwsRepository();
    }

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls") //parse the schema file created earlier
                .resolvers(new Query(awsRepository), new Mutation(awsRepository))
                .build()
                .makeExecutableSchema();
    }

}

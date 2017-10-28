package com.benjvi.awsql;

import com.benjvi.awsql.repositories.AwsRepository;
import com.benjvi.awsql.repositories.GcpRepository;
import com.coxautodev.graphql.tools.SchemaParser;
import javax.servlet.annotation.WebServlet;

import com.coxautodev.graphql.tools.SchemaParserBuilder;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

/**
 * Created by benjamin on 20/09/2017.
 */

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private static final AwsRepository awsRepository;
    private static final GcpRepository gcpRepository;

    static {
        awsRepository = new AwsRepository();
        gcpRepository = new GcpRepository();
    }

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .files(new String[]{
                        "schema.graphqls",
                        "aws_common.graphqls","aws_ec2_instance.graphqls","aws_ec2_vpc.graphqls",
                        "gcp_compute_instance.graphqls"
                }) //parse the schema file created earlier
                .resolvers(new Query(awsRepository, gcpRepository), new Mutation(awsRepository))
                .build()
                .makeExecutableSchema();
    }

}

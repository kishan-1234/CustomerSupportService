package org.example.services;

import org.example.exceptions.ServiceException;
import org.example.models.Agent;
import org.example.models.IssueType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AgentService {

    private static AgentService agentService;
    private static AtomicInteger agentId = new AtomicInteger(0);
    private Map<Integer, Agent> agents = new HashMap<>();
    public static AgentService getInstance() {
        if(agentService==null) {
            synchronized (AgentService.class) {
                if(agentService==null) {
                    agentService = new AgentService();
                }
            }
        }
        return agentService;
    }

    public int addAgent(String agentEmail, String agentName , List<IssueType> experties) {

        Agent agent = new Agent(agentId.incrementAndGet(), agentEmail, agentName, IssueType.getUniqueIssueType(experties));
        agents.put(agent.getAgentId(), agent);
        return agent.getAgentId();
    }

    public List<Agent> getAgentsByIssueType(IssueType issueType) {

        return agents.entrySet().stream().filter(agentEntry -> agentEntry.getValue().getExperties().contains(issueType))
                .map(agentEntry -> agentEntry.getValue())
                .collect(Collectors.toList());
    }

    public List<Agent> getAllAgents(){

        return agents.entrySet().stream().map(agentEntry -> agentEntry.getValue()).collect(Collectors.toList());
    }
}

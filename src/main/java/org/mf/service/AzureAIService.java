package org.mf.service;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import jakarta.enterprise.context.ApplicationScoped;
import org.mf.entity.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AzureAIService {

    private final OpenAIClient client;

    // Store conversation history in memory (for demo, per instance)
    private final List<ChatMessage> conversationHistory = new ArrayList<>();

    public AzureAIService() {
        this.client = new OpenAIClientBuilder()
                .credential(new AzureKeyCredential("key"))
                .endpoint("https://monic-mfbdyyrr-eastus2.cognitiveservices.azure.com/")
                .buildClient();

        // Optionally add a system prompt to guide the assistant behavior
        //conversationHistory.add(new ChatMessage(ChatRole.SYSTEM, "Eres un asistente que ayuda a encontrar empresas peruanas cuando te paso una palabra relacionada."));

    }

    public String getSemanticMatch(String input) {

        // Add the new user message to the conversation history
        //ChatMessage userMessage = new ChatMessage(ChatRole.USER, input);
        //conversationHistory.add(userMessage);

        //ChatCompletionsOptions completionsOptions = new ChatCompletionsOptions(conversationHistory);

        // Get response from Azure OpenAI
        //String assistantReply = client.getChatCompletions("gpt-35-turbo", completionsOptions)
        //        .getChoices()
        //        .get(0)
        //        .getMessage()
        //        .getContent();



        // Add assistant reply to conversation history for next calls
        //conversationHistory.add(new ChatMessage(ChatRole.ASSISTANT, assistantReply));

        String prompt = "Por favor, dame una lista de 100 nombres de las empresas peruanas más importantes relacionadas con '" + input + "' y conviertelo en un json con una lista de strings por ejemplo " +
                "[\n" + " \"Ripley\",\n" + " \"Repsol\",\n" + " \"Gas Natural de Lima\"\n" + "].";

        ChatMessage userMessage = new ChatMessage( ChatRole.USER, prompt );
        ChatCompletionsOptions completionsOptions = new ChatCompletionsOptions( Collections.singletonList(userMessage) );

        return client.getChatCompletions("gpt-35-turbo", completionsOptions)
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }

    // Optional: method to clear conversation history if needed
    //public void resetConversation() {
        //conversationHistory.clear();
        //conversationHistory.add(new ChatMessage(ChatRole.SYSTEM, "Eres un asistente que ayuda a encontrar empresas peruanas."));
    //}

}

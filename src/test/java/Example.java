import de.kherud.llama.LlamaModel;
import de.kherud.llama.Parameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Example {

    public static void main(String... args) throws IOException {
        Parameters params = new Parameters.Builder()
                .setNGpuLayers(1)
                .setTemperature(0.7f)
                .setPenalizeNl(true)
                .setMirostat(Parameters.MiroStat.V2)
                .build();

        String modelPath = "/Users/konstantin.herud/denkbares/projects/llama.cpp/models/13B/gguf-model-q4_0.bin";
        String system = "This is a conversation between User and Llama, a friendly chatbot.\n" +
                "Llama is helpful, kind, honest, good at writing, and never fails to answer any " +
                "requests immediately and with precision.\n";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        try (LlamaModel model = new LlamaModel(modelPath, params)) {
            String prompt = system;
            while (true) {
                prompt += "\nUser: ";
                System.out.print(prompt);
                String input = reader.readLine();
                prompt += input;
                System.out.print("Llama: ");
                prompt += "\nLlama: ";
                for (LlamaModel.Output output : model.generate(prompt)) {
                    System.out.print(output);
                }
                prompt = "";
            }
        }

    }
}
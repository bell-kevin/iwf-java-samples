package io.github.cadenceoss.iwf.spring.controller;

import io.github.cadenceoss.iwf.basic.BasicWorkflow;
import io.github.cadenceoss.iwf.core.Registry;
import io.github.cadenceoss.iwf.core.WorkerOptions;
import io.github.cadenceoss.iwf.core.WorkerService;
import io.github.cadenceoss.iwf.gen.models.WorkflowStateDecideRequest;
import io.github.cadenceoss.iwf.gen.models.WorkflowStateDecideResponse;
import io.github.cadenceoss.iwf.gen.models.WorkflowStateStartRequest;
import io.github.cadenceoss.iwf.gen.models.WorkflowStateStartResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiController {

    private WorkerService workerService;

    public ApiController() {
        final Registry registry = new Registry();
        registry.addWorkflow(new BasicWorkflow());
        workerService = new WorkerService(registry, WorkerOptions.defaultOptions);
    }

    @RequestMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Hello, world!");
    }

    @PostMapping("/api/v1/workflowState/start")
    public ResponseEntity<WorkflowStateStartResponse> apiV1WorkflowStateStartPost(
            final @RequestBody WorkflowStateStartRequest request
    ) {
        return ResponseEntity.ok(workerService.handleWorkflowStateStart(request));
    }

    @PostMapping("/api/v1/workflowState/decide")
    public ResponseEntity<WorkflowStateDecideResponse> apiV1WorkflowStateDecidePost(
            final @RequestBody WorkflowStateDecideRequest request
    ) {
        return ResponseEntity.ok(workerService.handleWorkflowStateDecide(request));
    }

}
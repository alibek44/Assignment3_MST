Assignment 3 — Minimum Spanning Tree (MST)
Author: Alibek Assylbekuly (SE-2422)

⸻

📘 Project Overview

This project implements and compares two fundamental Minimum Spanning Tree (MST) algorithms — Prim’s and Kruskal’s — using a custom Java graph structure.
The main goal is to evaluate both algorithms in terms of correctness, execution time, and operation count across multiple datasets of increasing graph size and density.

⸻

🧩 Algorithms Implemented
	•	Prim’s Algorithm: Builds MST by selecting minimum-weight edges that expand a connected set of vertices.
	•	Kruskal’s Algorithm: Sorts edges by weight and repeatedly joins the smallest edges without forming cycles, using a Disjoint Set (Union-Find) structure.
  
  📊 Experimental Results
<img width="476" height="164" alt="Screenshot 2025-10-26 at 01 24 00" src="https://github.com/user-attachments/assets/3d52407d-6a08-411f-8f99-faad4a042ba1" />
✔ Both algorithms yield identical MST costs.
⚡ Kruskal’s algorithm consistently demonstrates superior performance for medium to large graphs.

⸻

🧠 Conclusion

The results confirm theoretical predictions:
	•	Prim’s performs better for small or dense graphs.
	•	Kruskal’s excels with large or sparse graphs due to its efficient sorting and Union-Find operations.
	•	Both are correct and produce the same MST cost, but Kruskal’s is more scalable in practice.

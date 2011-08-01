# Introduction to genetic algorithms [Chapter 4]
Genetic algorithms are an attempt to model the evolution of living organisms by using algorithms.  
Every organisms grows according to the plan preserved in DNA, or RNA in case of simpler forms of life. Population of a given species is genetically varied. Thanks to that, it can survive sudden changes of the environment. Population members unfit for the new conditions die out, while the ones with needed characteristics (previously obsolete) now thrive and quickly rebuild the population. Now their genes appear more frequently within the population. Evolution takes place constantly, and organisms adapt to current environment, striving to dominate their ecospheres.  
When creating a new organism, nature rarely uses cloning. Combining the genotypes of two specimens is more frequent. Before the zygote is created, the spermatozoon and the ovum undergo slight mutations. Eventually, a new organism appears. Before leaving the mother’s body it undergoes a series of tests, to check whether it is worth of further “investments”. Hence the frequent miscarriages among mammals. Amphibians employ a different strategy – single insemination results in numerous offspring, of which only few survive. The more adapted an organism is to its current environment, the better his chances of passing on its genes. 

Genetic algorithms were developed by taking inspiration from these processes. They are heuristic algorithms, ideal for optimization of nontrivial functions.  

## Mechanism
A problem is atomized by dissecting it into genes. They are often the parameters of the function being optimized. Usually, it is assumed that all specimens have one chromosome of the same length. Each gene describes a different parameter of a solution. A fitness function is created for every gene, describing how good the solution is. 

The better the solution, the higher chance it has of producing offspring. Following methods of selection are used:

* Roulette method  
    Simple algorithm assigning each specimen a sector of a roulette wheel. The area of each fragment is directly proportional to the value of the fitness function 
* Tournament selection  
    The population is divided in two- or three member teams. One member of each team is selected to be transferred into parent pool of the next population. The selection of the transferred member can be either deterministic or nondeterministic (a variant of the deterministic method with the probability =1)
* Rank selection  
    Members of the population are sorted according to their fitness function, in order to establish a ranking. Frequency of appearing in the parent pool of a next generation is a function of ranking and not the fitness. 
* Deterministic method  
    Divides the fitness function into integer and fractional parts. Integer part determines the member’s frequency of appearing in the parent pool. This pool also includes members with biggest fractional parts. 

The way of passing the genes to offspring is defined by the operators. 
Commonly used are:

* Mutation operator  
    Operates on a single chromosome, creating a new chromosome with a randomly altered gene
* Crossover operator  
    Operates on two chromosomes, creating a new one using randomly combined genes of parents. Its variants include:
    * One-point crossover  
        Single crossover point
    * Two-point crossover  
        Two crossover points
    * Multipoint crossover  
        Generalization of the previous two methods
    * Uniform crossover  
        A randomly selected pattern determines the choice of the gene’s parent
* Inversion operator  
    Operates on a single chromosome, inverting its selected fragment. Rarely used in genetic algorithms, although frequent among living organisms. 

@GenericGenerator(name = "global_seq", type = EmbeddedIdSeqGenerator.class,
        parameters = {
                @Parameter(name = "sequence_name", value = "global_seq"),
                @Parameter(name = "increment_size", value = "1"),
        })
package com.github.neherim.entityid;

    import com.github.neherim.entityid.embedded.EmbeddedIdSeqGenerator;
    import org.hibernate.annotations.GenericGenerator;
    import org.hibernate.annotations.Parameter;